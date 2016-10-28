package controllers;

import akka.dispatch.MessageDispatcher;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import models.PozoEntity;
import play.libs.*;
import play.mvc.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static play.libs.Json.toJson;

/**
 * Created by mm.gomez10 on 28/08/2016.
 */
public class CampoController extends Controller{

    public CompletionStage<Result> getCampoJefe(long idJefe) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return CampoEntity.FINDER.where().eq("id_jefe_campo_username",idJefe).findUnique();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        campoEntities -> {
                            return ok(toJson(campoEntities));
                        }
                );
    }

    public CompletionStage<Result> getCampos() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return CampoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        campoEntities -> {
                            return ok(toJson(campoEntities));
                        }
                );
    }

    public CompletionStage<Result> createCampo(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nCampo = request().body().asJson();
        CampoEntity campo = Json.fromJson( nCampo , CampoEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    campo.save();
                    return campo;
                }
        ).thenApply(
                campoEntity -> {
                    return ok(Json.toJson(campoEntity));
                }
        );
    }

    public CompletionStage<Result> getCampo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return CampoEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                campoEntity -> {
                    return ok(Json.toJson(campoEntity));
                }
        );
    }

    public CompletionStage<Result> deleteCampo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    CampoEntity.FINDER.byId(id).delete();
                    return "Campo con id "+id+" eliminado";
                }
                ,jdbcDispatcher
        ).thenApply(
                campoEntity -> {
                    return ok(Json.toJson(campoEntity));
                }
        );
    }


    public CompletionStage<Result> getCampoReg(String reg){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return CampoEntity.FINDER.where().eq("region", reg).findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                campoEntity -> {
                    return ok(Json.toJson(campoEntity));
                }
        );
    }

    public CompletionStage<Result> getPozosAllCamposRegion(String reg) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            System.out.println("La region que llega es: "+reg);

                            int cuantosTotal = 0;
                            int cuantosAbiertos=0;
                            int cuantosParados=0;
                            int cuantosClausurados=0;
                            int cuantosProduccion=0;

                            List listaCamposDeRegion;
                            if(reg.equals("NACIONAL"))
                            {
                                listaCamposDeRegion = CampoEntity.FINDER.all();
                            }
                            else
                            {
                                listaCamposDeRegion = CampoEntity.FINDER.where().eq("region", reg).findList();
                            }
                            List listaDePozosEnRegion = new ArrayList();
                            for(int i=0;i< listaCamposDeRegion.size();i++)
                            {
                                Long idCampo = ((CampoEntity)listaCamposDeRegion.get(i)).getId();
                                List listaPozosDeCampo = PozoEntity.FINDER.where().eq("campo_id", idCampo ).findList();
                                cuantosTotal += listaPozosDeCampo.size();
                                for(int j=0;j<listaPozosDeCampo.size();j++)
                                {
                                    PozoEntity pozoActual = (PozoEntity)listaPozosDeCampo.get(j);
                                    switch (pozoActual.getEstado())
                                    {
                                        case ABIERTO:
                                            cuantosAbiertos+=1;
                                            break;
                                        case CLAUSURADO:
                                            cuantosClausurados+=1;
                                            break;
                                        case PRODUCCION:
                                            cuantosProduccion+=1;
                                            break;
                                        case PARADO:
                                            cuantosParados+=1;
                                            break;
                                    }
                                }
                            }

                            /**
                            String sql = "";
                            RawSql rawSql = RawSqlBuilder.parse(sql)
                                    // map the sql result columns to bean properties
                                    .columnMapping("order_id", "order.id")
                                    .columnMapping("o.status", "order.status")
                                    .columnMapping("c.id", "order.customer.id")
                                    .columnMapping("c.name", "order.customer.name")
                                    .create();

                            List list = Ebean.find(CampoEntity.class).setRawSql(rawSql).findList();*/

                            return "{ \"cuantos\":"+cuantosTotal+", \"clausurados\":"+cuantosClausurados+"," +
                                    "\"abiertos\":"+cuantosAbiertos+", \"parados\":"+cuantosParados+"," +"\"produccion\":"+
                             cuantosProduccion+ "}";

                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

}

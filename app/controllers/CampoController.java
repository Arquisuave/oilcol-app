package controllers;

import akka.dispatch.MessageDispatcher;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import models.PozoEntity;
import models.RegistroSensorBarrilesEntity;
import models.User;
import models.OilColPermission;
import play.libs.*;
import play.api.mvc.Result;
import play.mvc.*;
// import play.api.mvc.Result;
import scala.concurrent.Future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import be.objectify.deadbolt.scala.ActionBuilders;

import static play.libs.Json.toJson;

/**
 * Created by mm.gomez10 on 28/08/2016.
 */
public class CampoController extends Controller{

    public Future<Result> getCampoJefe(long idJefe) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return Utilities.toScala(CompletableFuture.
                supplyAsync(
                        () -> {
                            return CampoEntity.FINDER.where().eq("id_jefe_campo_username",idJefe).findUnique();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        campoEntities -> {
                            return ok(toJson(campoEntities)).asScala();
                        }
                ));
    }

    public Result getCampos(User user) {
        System.out.println(user);
        String perm = ((OilColPermission) user.getPermissions().head()).getValue();
        System.out.println(perm);
        // MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        // return Utilities.toScala(CompletableFuture.
                // supplyAsync(
                        // () -> {
                            return ok(toJson(CampoEntity.FINDER.all())).asScala();
                        // }
                        // ,jdbcDispatcher)
                // .thenApply(
                        // campoEntities -> {
                            // return ok(toJson(campoEntities)).asScala();
                        // }
                // ));
    }

    public Future<Result> createCampo(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nCampo = request().body().asJson();
        CampoEntity campo = Json.fromJson( nCampo , CampoEntity.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            campo.save();
                            return campo;
                        }
                ).thenApply(
                        campoEntity -> {
                            return ok(Json.toJson(campoEntity)).asScala();
                        }
                ));
    }

    public Future<Result> getCampo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            return CampoEntity.FINDER.byId(id);
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        campoEntity -> {
                            return ok(Json.toJson(campoEntity)).asScala();
                        }
                ));
    }

    public Future<Result> deleteCampo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            CampoEntity.FINDER.byId(id).delete();
                            return "Campo con id "+id+" eliminado";
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        campoEntity -> {
                            return ok(Json.toJson(campoEntity)).asScala();
                        }
                ));
    }


    public Future<Result> getCampoReg(String reg){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            return CampoEntity.FINDER.where().eq("region", reg).findList();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        campoEntity -> {
                            return ok(Json.toJson(campoEntity)).asScala();
                        }
                ));
    }

    public Future<Result> getPozosAllCamposRegion(String regG) {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return Utilities.toScala(CompletableFuture.
                        supplyAsync(
                                () -> {
                                    String reg = regG;
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
                                        if(reg.equals("PACIFICO")){reg = "PACIFICA";}
                                        if(reg.equals("AMAZONIA")){reg = "AMAZONAS";}
        
                                        listaCamposDeRegion = CampoEntity.FINDER.where().eq("region", reg).findList();
                                        System.out.println("analizando la region "+reg + " .la encontro "+ listaCamposDeRegion);
                                    }
                                    for(int i=0;i< listaCamposDeRegion.size();i++)
                                    {
                                        Long idCampo = ((CampoEntity)listaCamposDeRegion.get(i)).getId();
                                        List listaPozosDeCampo = PozoEntity.FINDER.where().eq("campo_id", idCampo ).findList();
                                        cuantosTotal += listaPozosDeCampo.size();
                                        System.out.println("id campo: "+idCampo);
                                        for(int j=0;j<listaPozosDeCampo.size();j++)
                                        {
                                            PozoEntity pozoActual = (PozoEntity)listaPozosDeCampo.get(j);
                                            System.out.println("pozo actual : "+pozoActual.getId());
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
                                    *String sql = "";
                                    *RawSql rawSql = RawSqlBuilder.parse(sql)
                                    *        // map the sql result columns to bean properties
                                    *        .columnMapping("order_id", "order.id")
                                    *        .columnMapping("o.status", "order.status")
                                    *        .columnMapping("c.id", "order.customer.id")
                                    *        .columnMapping("c.name", "order.customer.name")
                                    *        .create();
                                    *
                                    *List list = Ebean.find(CampoEntity.class).setRawSql(rawSql).findList();
                                    **/
                                    System.out.println("Termina de recolectar info de pozos");
                                    return "{ \"cuantos\":"+cuantosTotal+", \"clausurados\":"+cuantosClausurados+"," +
                                            "\"abiertos\":"+cuantosAbiertos+", \"parados\":"+cuantosParados+"," +"\"produccion\":"+
                                     cuantosProduccion+ "}";
        
                                }
                                ,jdbcDispatcher)
                        .thenApply(
                                pozoEntities -> {
                                    return ok(toJson(pozoEntities)).asScala();
                                }
                        ));
    }

}

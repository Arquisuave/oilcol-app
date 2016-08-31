package controllers;

import akka.dispatch.MessageDispatcher;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Junction;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import logic.RegistroSensorTempLogic;
import models.ParamReport;
import models.PozoEntity;
import models.RegistroSensorTempEntity;
import play.libs.Json;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ReportController extends Controller {

    public CompletionStage<Result> generateReport(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        System.out.print(params.toString());
        return CompletableFuture.supplyAsync(
                ()->{
                    Junction<RegistroSensorTempEntity> clause = RegistroSensorTempEntity.FINDER.where().conjunction();
                    if(params.getFechaInicio()!=null&&params.getFechaFin()!=null){
                        clause.between("TIMESTAMP", params.getFechaInicio(),params.getFechaFin());
                    }
                    else if(params.getFechaInicio()!=null){
                        clause.ge("TIMESTAMP",params.getFechaInicio());
                    }
                    else if(params.getFechaFin()!=null){
                        clause.le("TIMESTAMP",params.getFechaFin());
                    }
                    if(params.getJefeDeCampo()!=null){
                        clause.eq("pozo.campo.idJefeCampo.username",params.getJefeDeCampo());
                        System.out.print(params.getJefeDeCampo());
                    }
                    if(params.getRegion()!=null){
                        clause.ilike("pozo.campo.region","CARIBE");
                        System.out.print("regis");
                    }

                    return clause.findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                pozoEntities -> {
                    return ok(toJson(pozoEntities));
                }
        );
    }
}

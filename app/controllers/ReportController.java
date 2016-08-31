package controllers;

import akka.dispatch.MessageDispatcher;
import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Junction;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import logic.RegistroSensorTempLogic;
import logic.ReportLogic;
import models.*;
import play.libs.Json;
import play.mvc.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ReportController extends Controller {

    public CompletionStage<Result> generateReportTemp(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    Junction<RegistroSensorTempEntity> clause = RegistroSensorTempEntity.FINDER.where().conjunction();
                    ReportLogic.crearQuery(clause,params);
                    return clause.findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                registroEntities -> {
                    return ok(toJson(registroEntities));
                }
        );
    }



    public CompletionStage<Result> generateReportBarriles(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    Junction<RegistroSensorBarrilesEntity> clause = RegistroSensorBarrilesEntity.FINDER.where().conjunction();
                    ReportLogic.crearQuery(clause,params);
                    return clause.findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                registroEntities -> {
                    return ok(toJson(registroEntities));
                }
        );
    }

    public CompletionStage<Result> generateReportEmerg(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    Junction<RegistroSensorEmergEntity> clause = RegistroSensorEmergEntity.FINDER.where().conjunction();
                    ReportLogic.crearQuery(clause,params);
                    return clause.findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                registroEntities -> {
                    return ok(toJson(registroEntities));
                }
        );
    }

    public CompletionStage<Result> generateReportEner(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    Junction<RegistroSensorEnerEntity> clause = RegistroSensorEnerEntity.FINDER.where().conjunction();
                    ReportLogic.crearQuery(clause,params);
                    return clause.findList();
                }
                ,jdbcDispatcher
        ).thenApply(
                registroEntities -> {
                    return ok(toJson(registroEntities));
                }
        );
    }
}

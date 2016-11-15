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
import play.api.mvc.Result;
import play.mvc.*;
// import play.api.mvc.Result;
import scala.concurrent.Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by camilagarciahernandez on 8/30/16.
 */
public class ReportController extends Controller {

    public Future<Result> generateReportTemp(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            Junction<RegistroSensorTempEntity> clause = RegistroSensorTempEntity.FINDER.where().conjunction();
                            ReportLogic.crearQuery(clause,params);
                            return clause.findList();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities)).asScala();
                        }
                ));
    }



    public Future<Result> generateReportBarriles(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            Junction<RegistroSensorBarrilesEntity> clause = RegistroSensorBarrilesEntity.FINDER.where().conjunction();
                            ReportLogic.crearQuery(clause,params);
                            return clause.findList();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities)).asScala();
                        }
                ));
    }

    public Future<Result> generateReportEmerg(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            Junction<RegistroSensorEmergEntity> clause = RegistroSensorEmergEntity.FINDER.where().conjunction();
                            ReportLogic.crearQuery(clause,params);
                            return clause.findList();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities)).asScala();
                        }
                ));
    }

    public Future<Result> generateReportEner(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nParams = request().body().asJson();
        ParamReport params = Json.fromJson( nParams , ParamReport.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            Junction<RegistroSensorEnerEntity> clause = RegistroSensorEnerEntity.FINDER.where().conjunction();
                            ReportLogic.crearQuery(clause,params);
                            return clause.findList();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroEntities -> {
                            return ok(toJson(registroEntities)).asScala();
                        }
                ));
    }
}

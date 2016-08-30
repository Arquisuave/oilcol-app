package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import logic.RegistroSensorTempLogic;
import models.RegistroSensorBarrilesEntity;
import models.RegistroSensorEmergEntity;
import models.RegistroSensorEnerEntity;
import models.RegistroSensorTempEntity;
import play.libs.*;
import play.mvc.*;
import static play.mvc.Results.*;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

/**
 * Created by Margarita on 28/08/2016.
 */
public class RegistroSensorController extends Controller {
    //CRUD sensor barriles
    public CompletionStage<Result> getRegistrosSensorBarriles() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroSensorBarrilesEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroSensorBarrilesEntities -> {
                            return ok(toJson(registroSensorBarrilesEntities));
                        }
                );
    }

    public CompletionStage<Result> createRegistroSensorBarriles(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorBarriles = request().body().asJson();
        RegistroSensorBarrilesEntity reg = Json.fromJson( nRegistroSensorBarriles , RegistroSensorBarrilesEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    reg.save();
                    return reg;
                }
        ).thenApply(
                registroSensorBarrilesEntity -> {
                    return ok(Json.toJson(registroSensorBarrilesEntity));
                }
        );
    }

    public CompletionStage<Result> getRegistroSensorBarriles(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorBarrilesEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                registroSensorBarrilesEntity -> {
                    return ok(Json.toJson(registroSensorBarrilesEntity));
                }
        );
    }

    //CRUD sensor emergencia
    public CompletionStage<Result> getRegistrosSensorEmerg() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroSensorEmergEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroSensorEmergEntities -> {
                            return ok(toJson(registroSensorEmergEntities));
                        }
                );
    }

    public CompletionStage<Result> createRegistroSensorEmerg(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorEmerg = request().body().asJson();
        RegistroSensorEmergEntity reg = Json.fromJson( nRegistroSensorEmerg , RegistroSensorEmergEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    reg.save();
                    return reg;
                }
                ,jdbcDispatcher)
        .thenApply(
                registroSensorEmergEntity -> {
                    return ok(Json.toJson(registroSensorEmergEntity));
                }
        );
    }

    public CompletionStage<Result> getRegistroSensorEmerg(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorEmergEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                registroSensorEmergEntity -> {
                    return ok(Json.toJson(registroSensorEmergEntity));
                }
        );
    }

    //CRUD sensor energía
    public CompletionStage<Result> getRegistrosSensorEner() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroSensorEnerEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroSensorEnerEntities -> {
                            return ok(toJson(registroSensorEnerEntities));
                        }
                );
    }

    public CompletionStage<Result> createRegistroSensorEner(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorEner = request().body().asJson();
        RegistroSensorEnerEntity reg = Json.fromJson( nRegistroSensorEner , RegistroSensorEnerEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    reg.save();
                    return reg;
                }
        ).thenApply(
                registroSensorEnerEntity -> {
                    return ok(Json.toJson(registroSensorEnerEntity));
                }
        );
    }

    public CompletionStage<Result> getRegistroSensorEner(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorEnerEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                registroSensorEnerEntity -> {
                    return ok(Json.toJson(registroSensorEnerEntity));
                }
        );
    }

    // CRUD sensor temperatura

    public CompletionStage<Result> getRegistrosSensorTemp() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return RegistroSensorTempEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        registroSensorTempEntities -> {
                            return ok(toJson(registroSensorTempEntities));
                        }
                );
    }

    public CompletionStage<Result> createRegistroSensorTemp(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorTemp = request().body().asJson();
        RegistroSensorTempEntity reg = Json.fromJson( nRegistroSensorTemp , RegistroSensorTempEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorTempLogic.actualizarRegistro(reg);
                }
                ,jdbcDispatcher)
        .thenApply(
                registroSensorTempEntity -> {
                    return ok(Json.toJson(registroSensorTempEntity));
                }
        );
    }

    public CompletionStage<Result> getRegistroSensorTemp(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorTempEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                registroSensorTempEntity -> {
                    return ok(Json.toJson(registroSensorTempEntity));
                }
        );
    }

}

package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import logic.RegistroSensorBarrilesLogic;
import logic.RegistroSensorEnerLogic;
import logic.RegistroSensorTempLogic;
import models.*;
import play.libs.*;
import play.api.mvc.Result;
import play.mvc.*;
// import play.api.mvc.play.mvc.Result;
import scala.concurrent.Future;

import static play.mvc.Results.*;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

/**
 * Created by Margarita on 28/08/2016.
 */
public class RegistroSensorController extends Controller {
    //CRUD sensor barriles
    public CompletionStage<play.mvc.Result> getRegistrosSensorBarriles() {
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

    public CompletionStage<play.mvc.Result> createRegistroSensorBarriles(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorBarriles = request().body().asJson();
        RegistroSensorBarrilesEntity reg = Json.fromJson( nRegistroSensorBarriles , RegistroSensorBarrilesEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    RegistroSensorBarrilesEntity result = RegistroSensorBarrilesLogic.crearActualizarRegistro(reg);
                    return result;
                }
        ).thenApply(
                registroSensorBarrilesEntity -> {
                    if (registroSensorBarrilesEntity==null){
                        return forbidden("Pozo no esta habilitado");
                    }
                    return ok(Json.toJson(registroSensorBarrilesEntity));
                }
        );
    }

    public CompletionStage<play.mvc.Result> getRegistroSensorBarriles(Long id){
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
    public CompletionStage<play.mvc.Result> getRegistrosSensorEmerg() {
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

    public CompletionStage<play.mvc.Result> createRegistroSensorEmerg(){
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

    public CompletionStage<play.mvc.Result> getRegistroSensorEmerg(Long id){
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
    public CompletionStage<play.mvc.Result> getRegistrosSensorEner() {
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

    public CompletionStage<play.mvc.Result> createRegistroSensorEner(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nRegistroSensorEner = request().body().asJson();
        RegistroSensorEnerEntity reg = Json.fromJson( nRegistroSensorEner , RegistroSensorEnerEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    return RegistroSensorEnerLogic.promediar(reg);
                }
        ).thenApply(
                registroSensorEnerEntity -> {
                    if (registroSensorEnerEntity==null){
                        return forbidden("Pozo no esta habilitado");
                    }
                    return ok(Json.toJson(registroSensorEnerEntity));
                }
        );
    }

    public CompletionStage<play.mvc.Result> getRegistroSensorEner(Long id){
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

    public CompletionStage<play.mvc.Result> getRegistrosSensorTemp() {
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

    public CompletionStage<play.mvc.Result> createRegistroSensorTemp(){
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
                    if (registroSensorTempEntity==null){
                        return forbidden("Pozo no esta habilitado");
                    }
                    return ok(Json.toJson(registroSensorTempEntity));
                }
        );
    }

    public CompletionStage<play.mvc.Result> getRegistroSensorTemp(Long id){
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

    public Future<Result> getLastRegistroSensorTemp(long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            return RegistroSensorTempEntity.FINDER.where().eq("pozo_id",id).orderBy("id_sensor_temp desc").setMaxRows(1).findUnique();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroSensorTempEntity -> {
                            return ok(Json.toJson(registroSensorTempEntity)).asScala();
                        }
                ));
    }

    public Future<Result> getLastRegistroSensorBarr(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            return RegistroSensorBarrilesEntity.FINDER.where().eq("pozo_id",id).orderBy("id_sensor_barriles desc").setMaxRows(1).findUnique();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroSensorBarrilesEntity -> {
                            return ok(Json.toJson(registroSensorBarrilesEntity)).asScala();
                        }
                ));
    }

    public Future<Result> getLastRegistroSensorEner(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                        ()->{
                            return RegistroSensorEnerEntity.FINDER.where().eq("pozo_id",id).orderBy("id_sensor_ener desc").setMaxRows(1).findUnique();
                        }
                        ,jdbcDispatcher
                ).thenApply(
                        registroSensorEnerEntity -> {
                            return ok(Json.toJson(registroSensorEnerEntity)).asScala();
                        }
                ));
    }

}

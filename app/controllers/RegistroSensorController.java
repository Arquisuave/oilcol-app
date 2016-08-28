package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.RegistroSensorBarrilesEntity;
import play.libs.*;
import play.mvc.*;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

/**
 * Created by Margarita on 28/08/2016.
 */
public class RegistroSensorController {
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
}

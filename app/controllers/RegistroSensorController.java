package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import play.libs.Json;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by Margarita on 28/08/2016.
 */
public class SensorController {

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
}

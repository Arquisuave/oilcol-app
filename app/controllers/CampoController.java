package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import play.libs.*;
import play.mvc.*;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

/**
 * Created by mm.gomez10 on 28/08/2016.
 */
public class CampoController extends Controller{

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

}

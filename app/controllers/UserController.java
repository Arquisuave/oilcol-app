package controllers;

import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import play.mvc.*;
import static play.libs.Json.toJson;
import models.AuthEntity;
import models.UsuarioEntity;
import models.AuthResponse;
import akka.dispatch.MessageDispatcher;
import java.util.Date;


import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

public class UserController extends Controller 
{
    public CompletionStage<Result> getUsers(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return UsuarioEntity.FINDER.all();
                }
                ,jdbcDispatcher
        ).thenApply(
                entity -> {
                    return ok(Json.toJson(entity));
                }
        );
    }

    public CompletionStage<Result> createUser(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nUser = request().body().asJson();
        UsuarioEntity user = Json.fromJson(nUser, UsuarioEntity.class);
        return CompletableFuture.supplyAsync(
                ()->{
                    user.save();
                    return user;
                }
                ,jdbcDispatcher
        ).thenApply(
                entity -> {
                    return ok(Json.toJson(entity));
                }
        );
    }    
}
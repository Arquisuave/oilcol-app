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
}
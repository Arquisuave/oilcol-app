package controllers;

import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import play.mvc.*;
import static play.libs.Json.toJson;
import models.AuthEntity;
import akka.dispatch.MessageDispatcher;

import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class AuthController extends Controller {

    public CompletionStage<Result> authenticate(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nAuth = request().body().asJson();
        AuthEntity product = Json.fromJson( nAuth , AuthEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    product.save();
                    return product;
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson());
                }
        );
    }

}
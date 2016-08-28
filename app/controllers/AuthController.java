package controllers;

import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import play.mvc.*;
import static play.libs.Json.toJson;
import models.AuthEntity;
import models.UsuarioEntity;
import akka.dispatch.MessageDispatcher;


import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;
import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;

public class AuthController extends Controller {

    public CompletionStage<Result> authenticate(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nAuth = request().body().asJson();
        AuthEntity auth = Json.fromJson( nAuth , AuthEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    UsuarioEntity user = UsuarioEntity.FINDER.byId(auth.getUsername());
                    if(user != null)
                    {
                        DigestSHA3 md = new SHA3.DigestSHA3(512);
                        byte[] digest = md.digest(user.getPassword().getBytes());
                        String hash = hashToString(digest);
                        return product;
                    }
                }
                ,jdbcDispatcher
        ).thenApply(
                entity -> {
                    return ok(entity.toJson());
                }
        );
    }

    private static String hashToString(byte[] hash) {
        StringBuilder buff = new StringBuilder();

        for (byte b : hash) {
            buff.append(String.format("%02x", b & 0xFF));
        }
        
        return buff.toString();
    }

}
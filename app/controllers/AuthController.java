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

public class AuthController extends Controller {

    public CompletionStage<Result> authenticate(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nAuth = request().body().asJson();
        AuthEntity auth = Json.fromJson( nAuth , AuthEntity.class ) ;
        AuthResponse resp = new AuthResponse(null, "", null);
        return CompletableFuture.supplyAsync(
                ()->{
                    UsuarioEntity user = UsuarioEntity.FINDER.byId(auth.getUsername());
                    if(user != null)
                    {
                        DigestSHA3 md = new SHA3.DigestSHA3(256);
                        byte[] digest = md.digest(user.getPassword().getBytes());
                        String hash = hashToString(digest);
                        System.out.println(hash);
                        System.out.println(auth.getPassword());
                        if(hash.equals(auth.getPassword()))
                        {
                            Long token = System.currentTimeMillis() / 1000L;
                            String tok = token.toString();
                            tok = tok + user.getUsername();
                            byte[] b = md.digest(tok.getBytes());
                            tok = hashToString(b);
                            resp.setToken(tok);
                            resp.setTimestamp(token);
                            resp.setMessage("Login successful!");
                        }
                        else
                        {
                            resp.setMessage("Wrong User/Password combination");
                        }
                    }
                    else
                    {
                        resp.setMessage("User does not exist");
                    }
                    return resp; 

                }
                ,jdbcDispatcher
        ).thenApply(
                entity -> {
                    if(entity.getToken() != null)
                    {
                        return ok(Json.toJson(entity));
                    }
                    else
                    {
                        return unauthorized(Json.toJson(entity));
                    }
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
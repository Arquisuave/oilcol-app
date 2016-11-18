package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.User;
import models.UsuarioEntity;
import play.api.mvc.Result;
import play.libs.Json;
import play.mvc.Controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

public class UserControllerAuthNOOriginal extends Controller
{

    public Result getUser(User user)
    {
        return ok(toJson(user.getName())).asScala();
    }
}
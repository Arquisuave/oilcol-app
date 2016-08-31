package controllers;

import dispatchers.AlertDispatcher;
import java.util.concurrent.CompletableFuture;
import play.mvc.*;
import static play.libs.Json.toJson;
import models.RegistroSensorEmergEntity;
import models.UsuarioEntity;
import akka.dispatch.MessageDispatcher;
import java.util.Date;


import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class AlertController extends Controller {

    public CompletionStage<Result> reportAlert(){
        MessageDispatcher alertDispatcher = AlertDispatcher.alertDispatcher;
        JsonNode nAlert = request().body().asJson();
        RegistroSensorEmergEntity pozo = Json.fromJson(nAlert, RegistroSensorEmergEntity.class);
        return CompletableFuture.supplyAsync(
                ()->{
                                             

                }
                ,alertDispatcher
        ).thenApply(
                entity -> {
                    
                }
        );
    }

}
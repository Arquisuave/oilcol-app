package controllers;

import dispatchers.AlertDispatcher;
import dispatchers.AkkaDispatcher;
import java.util.concurrent.CompletableFuture;
import play.mvc.*;
import static play.libs.Json.toJson;
import models.RegistroSensorEmergEntity;
import models.NotificationEntity;
import models.UsuarioEntity;
import models.PozoEntity;
import akka.dispatch.MessageDispatcher;
import java.util.Date;


import java.util.concurrent.CompletionStage;
import play.libs.Json;
import com.fasterxml.jackson.databind.JsonNode;

public class AlertController extends Controller {

    public CompletionStage<Result> reportAlert(){
        MessageDispatcher alertDispatcher = AlertDispatcher.alertDispatcher;
        JsonNode nAlert = request().body().asJson();
        RegistroSensorEmergEntity alert = Json.fromJson(nAlert, RegistroSensorEmergEntity.class);
        return CompletableFuture.supplyAsync(
                ()->{
                     alert.save();
                     PozoEntity p = PozoEntity.FINDER.byId(alert.getPozo().getId());
                     UsuarioEntity u = p.getCampo().getIdJefeCampo();
                     NotificationEntity not = new NotificationEntity(0L, u, "Alerta: Pozo "+p.getId()+" reporta una emergencia de tipo: "+alert.getTipo().getType(), alert);
                     not.save();
                     return "";
                }
                ,alertDispatcher
        ).thenApply(
                entity -> {
                   return ok(entity);
                }
        );
    }

    public CompletionStage<Result> removeNotification(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        NotificationEntity n = new NotificationEntity(id);
        return CompletableFuture.supplyAsync(
                ()->{
                     n.delete();
                     return "Deletion successful!";
                }
                ,jdbcDispatcher
        ).thenApply(
                entity -> {
                   return ok(entity);
                }
        );
    }




}
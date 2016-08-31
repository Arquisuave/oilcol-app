package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import models.MensajeDeUsuarioDTO;
import models.PozoEntity;
import models.UsuarioEntity;
import play.libs.Json;
import play.mvc.*;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

public class PozoController extends Controller{

    public CompletionStage<Result> getPozos() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities));
                        }
                );
    }

    public CompletionStage<Result> createPozo(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nPozo = request().body().asJson();
        PozoEntity pozo = Json.fromJson( nPozo , PozoEntity.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    pozo.save();
                    return pozo;
                }
                ,jdbcDispatcher
        ).thenApply(
                pozoEntity -> {
                    return ok(Json.toJson(pozoEntity));
                }
        );
    }

    public CompletionStage<Result> updatePozo()
    {
        //debe llegar asi:
        //{ "user":"jg",
        //  "password" : "123",
        //  "type":"JEFE_DE_CAMPO",
        //  "payload":"{"Status":"enProduccion" , "Id": 123}"}
        // o "parado"   si es JEFE_DE_PRODUCCION el status puede ser abierto o clausurado

        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        //llega el json con el username, password, type, payload
        JsonNode nMessage = request().body().asJson();
        MensajeDeUsuarioDTO mensajeCompleto  = Json.fromJson( nMessage, MensajeDeUsuarioDTO.class ) ;

        JsonNode payload = Json.parse(mensajeCompleto.getPayload());

        String nuevoStatus = payload.get("Status").toString();
        String idPozo = payload.get("Id").toString();

        //usuario.setPassword(mensajeCompleto.getPassword());
        //usuario.setUsername(mensajeCompleto.getUser());
        //usuario.setType(usuario.getTipoUsuario(mensajeCompleto.getType()) );

        return CompletableFuture.supplyAsync(
                ()->{
                    //verifica el campo del jefe
                    if (mensajeCompleto.getUser()!=null)
                    {
                        UsuarioEntity usuario = UsuarioEntity.FINDER.where().eq("user", mensajeCompleto.getUser()).eq("password",mensajeCompleto.getPassword()).setMaxRows(1).findUnique();
                        if(usuario != null && usuario.getType() == ( usuario.getTipoUsuario(mensajeCompleto.getType())))
                        {
                            //si es jefe
                            //CampoEntity campo = CampoEntity.FINDER.where().eq("jefeCampo", usuario.getUsername());
                            //public TipoUsuario getTipoUsuario(String tipo)
                            {
                               // return TipoUsuario.valueOf(tipo);
                            }
                        }
                        else
                        {
                            // mal usuario
                            return null;
                        }
                    }
                    else
                    {

                    }
                    //pozo.update();
                    return null;
                }
                ,jdbcDispatcher
        ).thenApply(
                pozoEntity -> {
                    return ok(Json.toJson(pozoEntity));
                }
        );
    }


    public CompletionStage<Result> getPozo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return CompletableFuture.supplyAsync(
                ()->{
                    return PozoEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity));
                }
        );
    }
}

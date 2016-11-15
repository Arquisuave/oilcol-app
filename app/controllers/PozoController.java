package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.CampoEntity;
import models.MensajeDeUsuarioDTO;
import models.PozoEntity;
import models.UsuarioEntity;
import play.libs.Json;
import play.api.mvc.Result;
import play.mvc.*;
// import play.api.mvc.Result;
import scala.concurrent.Future;

import java.util.concurrent.*;

import static play.libs.Json.toJson;

public class PozoController extends Controller{

    public Future<Result> getPozos() {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return Utilities.toScala(CompletableFuture.
                supplyAsync(
                        () -> {
                            return PozoEntity.FINDER.all();
                        }
                        ,jdbcDispatcher)
                .thenApply(
                        pozoEntities -> {
                            return ok(toJson(pozoEntities)).asScala();
                        }
                ));
    }

    public Future<Result> createPozo(){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        JsonNode nPozo = request().body().asJson();
        PozoEntity pozo = Json.fromJson( nPozo , PozoEntity.class ) ;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                ()->{
                    pozo.save();
                    return pozo;
                }
                ,jdbcDispatcher
        ).thenApply(
                pozoEntity -> {
                    return ok(Json.toJson(pozoEntity)).asScala();
                }
        ));
    }

    public Future<Result> updatePozo(Long id, String nuevoEstado)
    {
        PozoEntity.Estado estadito =PozoEntity.Estado.valueOf(nuevoEstado);
        System.out.println(estadito);
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                ()->{
                    PozoEntity pozoo = PozoEntity.FINDER.byId(id);
                    pozoo.setEstado(estadito);
                    System.out.println(pozoo.getEstado());
                    pozoo.save();
                    return PozoEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity)).asScala();
                }
        ));
    }


    public Future<Result> getPozo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                ()->{
                    return PozoEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity)).asScala();
                }
        ));
    }

    public Future<Result> deletePozo(Long id){
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                ()->{
                    PozoEntity.FINDER.byId(id).delete();
                    return "Pozo con id "+id+" eliminado";
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity)).asScala();
                }
        ));
    }
}

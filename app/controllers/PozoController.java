package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.*;
import play.libs.Json;
import play.api.mvc.Result;
import play.mvc.*;
// import play.api.mvc.Result;
import scala.concurrent.Future;

import java.util.List;
import java.util.concurrent.*;

import static play.libs.Json.toJson;

public class PozoController extends Controller{

    public Result getPozos(User user ) {
        System.out.println(user);
        String perm = ((OilColPermission) user.getPermissions().head()).getValue();
        System.out.println(perm);
        if(perm.equals("ALL"))
        {
            return ok(toJson(PozoEntity.FINDER.all())).asScala();
        }
        else
        {
             List pozosL = PozoEntity.FINDER.where().eq("campo.id",Long.parseLong(perm)).findList();
            System.out.println(pozosL.size());
            return ok(toJson(pozosL)).asScala();
        }
        /**
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
                ));*/
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


    public Result getPozo(User user, Long id){
        System.out.println(user);
        String perm = ((OilColPermission) user.getPermissions().head()).getValue();
        System.out.println(perm);
        if(perm.equals("ALL"))
        {
            return ok(toJson(PozoEntity.FINDER.byId(id))).asScala();
        }
        else
        {
            List pozosL = PozoEntity.FINDER.where().eq("campo.id",Long.parseLong(perm)).findList();
            System.out.println(pozosL.size());
            PozoEntity pozo = null;
            for(int i=0; i< pozosL.size() && pozo==null;i++)
            {
                PozoEntity actual = ((PozoEntity)pozosL.get(i));
                if(actual.getId() == id)
                {
                    pozo = actual;
                }
            }
            return ok(toJson(pozo)).asScala();
        }
       /** MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;
        return Utilities.toScala(CompletableFuture.supplyAsync(
                ()->{
                    return PozoEntity.FINDER.byId(id);
                }
                ,jdbcDispatcher
        ).thenApply(
                productEntity -> {
                    return ok(Json.toJson(productEntity)).asScala();
                }
        ));*/
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

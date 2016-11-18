package filters;

import akka.stream.Materializer;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.Function;
import javax.inject.*;
import play.mvc.*;
import static play.mvc.Results.*;
import play.mvc.Http.RequestHeader;
import models.*;
import static models.UsuarioEntity.TipoUsuario;
import static play.libs.Json.toJson;
import play.libs.Json;
import java.util.concurrent.CompletableFuture;
import java.util.Map;


/**
 * This is a simple filter that adds a header to all requests. It's
 * added to the application's list of filters by the
 */
@Singleton
public class AuthFilter extends Filter {

    private final Executor exec;

    /**
     * @param mat This object is needed to handle streaming of requests
     * and responses.
     * @param exec This class is needed to execute code asynchronously.
     * It is used below by the <code>thenAsyncApply</code> method.
     */
    @Inject
    public AuthFilter(Materializer mat, Executor exec) {
        super(mat);
        this.exec = exec;
    }

    @Override
    public CompletionStage<Result> apply(
        Function<RequestHeader, CompletionStage<Result>> next,
        RequestHeader requestHeader) {
        Map<String, String> tags = requestHeader.tags();
        String route = tags.get("ROUTE_PATTERN");
        System.out.println(requestHeader.method());
        if(requestHeader.method().equals("OPTIONS")){
            return next.apply(requestHeader).thenApplyAsync(
                    result -> result.withHeader("Access-Control-Allow-Headers","oilcol-token,content-type").withHeader("Access-Control-Allow-Method","*"),
                    exec
            );
        }
        if(route.equals("/registro/temp") || route.equals("/registro/barril") || route.equals("/registro/energia"))
        {
            if(requestHeader.hasHeader("Passwordcita"))
            {
                return next.apply(requestHeader).thenApplyAsync(
                    result -> result.withHeader("X-ExampleFilter", "foo"),
                    exec
                );
            }
            else
            {
                return CompletableFuture.supplyAsync(
                    () ->
                    {
                        return new AuthResponse(null, "Restricted Access Area", System.currentTimeMillis() / 1000L, TipoUsuario.NONE);
                    }
                ).thenApply(
                    entity -> {
                        return forbidden(Json.toJson(entity));
                    }
                );
            }
        }
        else
        {
            return next.apply(requestHeader).thenApplyAsync(
                    result -> result.withHeader("X-ExampleFilter", "foo"),
                    exec
                );
        }
    }

}

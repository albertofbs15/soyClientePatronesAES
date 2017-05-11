package http;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.model.StatusCodes;
import akka.http.javadsl.server.*;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import domain.calculadora.Calculadora;
import domain.calculadora.Suma;
import util.JacksonJdk8;

import java.time.LocalDate;
import java.util.concurrent.CompletionStage;

/**
 * Created by AHernandezS on 22/03/2017.
 */

public class HttpAesDirective extends AllDirectives {

    Calculadora calculadora = new Calculadora();

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        HttpAesDirective app = new HttpAesDirective();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("0.0.0.0", 9090), materializer);

        System.out.println("Server online at http://localhost:9090/\nPress RETURN to stop...");
        System.in.read();

        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute() {
        return route(
                pathPrefix("suma", () ->
                        route(
                                post(() -> entity(JacksonJdk8.unmarshaller(Suma.class), hacerSuma -> handleSuma(hacerSuma)))
                        )
                )
        );
    }

    private Route handleSuma(Suma suma) {
        System.out.println(LocalDate.now() + ": handleSuma ");
        return complete(StatusCodes.OK, calculadora.suma(suma), Jackson.<Integer>marshaller());
    }

}

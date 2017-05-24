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
import domain.Convenio.IntermediateRouting;
import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Factura;
import domain.Convenio.entity.Pago;

import java.time.LocalDate;
import java.util.concurrent.CompletionStage;

/**
 * Created by AHernandezS on 22/03/2017.
 */

public class HttpAesDirective extends AllDirectives {

    IntermediateRouting intermediateRouting = new IntermediateRouting();

    public static void main(String[] args) throws Exception {
        ActorSystem system = ActorSystem.create("routes");

        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);

        HttpAesDirective app = new HttpAesDirective();

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = app.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> binding = http.bindAndHandle(routeFlow,
                ConnectHttp.toHost("localhost", 9090), materializer);

        System.out.println("Server online at http://localhost:9090/\nPress RETURN to stop...");
        System.in.read();

        binding.thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
    }

    private Route createRoute() {
        return route(
                pathPrefix("servicios", () ->
                        pathPrefix("factura", () ->
                                route(
                                        get(() -> route(path(idFactura -> handleConsultarFactura(idFactura))))
                                        ,
                                        put(() -> route(pathPrefix(facturaId -> route(
                                                pathPrefix("pagar", () -> handlePagarFactura(null)),
                                                pathPrefix("compensar", () -> handleCompensarPagoFactura(null))
                                        ))))
                                )
                        )
                )
        );
    }

    private Route handleConsultarFactura(String idFactura) {
        System.out.println(LocalDate.now() + ": handleConsultarFactura ");
        return complete(StatusCodes.OK, intermediateRouting.consultarFactura(idFactura), Jackson.<Factura>marshaller());
    }

    private Route handlePagarFactura(Pago pago) {
        System.out.println(LocalDate.now() + ": handlePagarFactura ");
        return complete(StatusCodes.OK, intermediateRouting.pagoFactura(pago), Jackson.<Boolean>marshaller());
    }

    private Route handleCompensarPagoFactura(Compensacion compensacion) {
        System.out.println(LocalDate.now() + ": handleCompensarPagoFactura ");
        return complete(StatusCodes.OK, intermediateRouting.compensarPagoFactura(compensacion), Jackson.<Boolean>marshaller());
    }

}

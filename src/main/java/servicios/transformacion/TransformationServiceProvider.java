package servicios.transformacion;

import akka.actor.ActorSystem;
import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Pago;
import domain.Convenio.entity.Respuesta;
import http.core.HttpServiceClient;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public class TransformationServiceProvider implements TransformacionService {
    private static final ActorSystem system = ActorSystem.create();
    private final int BUFFER_SIZE = 5000;
    final HttpServiceClient httpClient;

    public TransformationServiceProvider() {
        this.httpClient = new HttpServiceClient(system, BUFFER_SIZE);
    }

    @Override
    public CompletableFuture<String> getPayloadParaPagos(String idFactura, Pago pago) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }
    @Override
    public CompletableFuture<String> getPayloadParaCompensacionDePagos(String idFactura, Compensacion compensacion) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }
    @Override
    public CompletableFuture<Respuesta> getResponseDeConsulta(String idFactura, String payload) {
        String uri = "";
        return httpClient.doGet(uri, Respuesta.class).toCompletableFuture();
    }

    @Override
    public CompletableFuture<Respuesta> getResponseParaPagos(String idFactura, String payload) {
        String uri = "";
        return httpClient.doGet(uri, Respuesta.class).toCompletableFuture();
    }

    @Override
    public CompletableFuture<Respuesta> getResponseParaCompensacionDePagos(String idFactura, String payload) {
        String uri = "";
        return httpClient.doGet(uri, Respuesta.class).toCompletableFuture();
    }
}

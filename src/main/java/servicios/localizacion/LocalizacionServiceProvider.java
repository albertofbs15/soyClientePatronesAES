package servicios.localizacion;

import akka.actor.ActorSystem;

import java.util.concurrent.CompletableFuture;
import http.core.HttpServiceClient;

/**
 * Created by alber on 5/24/2017.
 */
public class LocalizacionServiceProvider implements LocalizacionService {
    private static final ActorSystem system = ActorSystem.create();
    private final int BUFFER_SIZE = 5000;
    final HttpServiceClient httpClient;

    public LocalizacionServiceProvider() {
        this.httpClient = new HttpServiceClient(system, BUFFER_SIZE);
    }

    @Override
    public CompletableFuture<String> getEndpointDeConsulta(String idFactura) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }

    @Override
    public CompletableFuture<String> getEndpointParaPagos(String idFactura) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }

    @Override
    public CompletableFuture<String> getEndpointParaCompensacionDePagos(String idFactura) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }
}

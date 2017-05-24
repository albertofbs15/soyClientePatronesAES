package servicios.dispatcher;

import akka.actor.ActorSystem;
import http.core.HttpServiceClient;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public class DispatcherServiceProvider implements DispatcherService {

    private static final ActorSystem system = ActorSystem.create();
    private final int BUFFER_SIZE = 5000;
    final HttpServiceClient httpClient;

    public DispatcherServiceProvider() {
        this.httpClient = new HttpServiceClient(system, BUFFER_SIZE);
    }

    @Override
    public CompletableFuture<String> sendRequest(String idFactura, String payload) {
        String uri = "";
        return httpClient.doGet(uri, String.class).toCompletableFuture();
    }
}

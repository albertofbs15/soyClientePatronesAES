package servicios.dispatcher;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public interface DispatcherService {
    CompletableFuture<String> sendRequest (String idFactura, String payload);
}

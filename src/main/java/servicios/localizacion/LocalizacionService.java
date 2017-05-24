package servicios.localizacion;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public interface LocalizacionService {
    CompletableFuture<String> getEndpointDeConsulta (String idFactura);
    CompletableFuture<String> getEndpointParaPagos (String idFactura);
    CompletableFuture<String> getEndpointParaCompensacionDePagos (String idFactura);
}

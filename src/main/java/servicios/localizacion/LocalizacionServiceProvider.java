package servicios.localizacion;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public class LocalizacionServiceProvider implements LocalizacionService {

    @Override
    public CompletableFuture<String> getEndpointDeConsulta(String idFactura) {
        return null;
    }

    @Override
    public CompletableFuture<String> getEndpointParaPagos(String idFactura) {
        return null;
    }

    @Override
    public CompletableFuture<String> getEndpointParaCompensacionDePagos(String idFactura) {
        return null;
    }
}

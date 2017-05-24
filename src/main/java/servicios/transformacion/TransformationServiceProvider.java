package servicios.transformacion;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Pago;
import domain.Convenio.entity.Respuesta;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public class TransformationServiceProvider implements TransformacionService {
    @Override
    public CompletableFuture<String> getPayloadParaPagos(String idFactura, Pago pago) {
        return null;
    }
    @Override
    public CompletableFuture<String> getPayloadParaCompensacionDePagos(String idFactura, Compensacion compensacion) {
        return null;
    }
    @Override
    public CompletableFuture<Respuesta> getResponseDeConsulta(String idFactura, String payload) {
        return null;
    }

    @Override
    public CompletableFuture<Respuesta> getResponseParaPagos(String idFactura, String payload) {
        return null;
    }

    @Override
    public CompletableFuture<Respuesta> getResponseParaCompensacionDePagos(String idFactura, String payload) {
        return null;
    }
}

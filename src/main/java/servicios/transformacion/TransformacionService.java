package servicios.transformacion;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Pago;
import domain.Convenio.entity.Respuesta;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public interface TransformacionService {
    CompletableFuture<String> getPayloadParaPagos (String idFactura, Pago pago);
    CompletableFuture<String> getPayloadParaCompensacionDePagos (String idFactura, Compensacion compensacion);

    CompletableFuture<Respuesta> getResponseDeConsulta (String idFactura, String payload);
    CompletableFuture<Respuesta> getResponseParaPagos (String idFactura, String payload);
    CompletableFuture<Respuesta> getResponseParaCompensacionDePagos (String idFactura, String payload);
}

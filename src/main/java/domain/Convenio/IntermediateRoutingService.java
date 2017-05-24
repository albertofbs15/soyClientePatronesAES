package domain.Convenio;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Factura;
import domain.Convenio.entity.Pago;
import domain.Convenio.entity.Respuesta;

import java.util.concurrent.CompletableFuture;

/**
 * Created by alber on 5/24/2017.
 */
public interface IntermediateRoutingService {
    CompletableFuture<Respuesta> consultarFactura (String idFactura);
    CompletableFuture<Respuesta> pagoFactura (Pago pago);
    CompletableFuture<Respuesta> compensarPagoFactura (Compensacion compensacion);
}

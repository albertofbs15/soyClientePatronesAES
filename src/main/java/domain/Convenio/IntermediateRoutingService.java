package domain.Convenio;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Factura;
import domain.Convenio.entity.Pago;

/**
 * Created by alber on 5/24/2017.
 */
public interface IntermediateRoutingService {
    Factura consultarFactura (String idFactura);
    boolean pagoFactura (Pago pago);
    boolean compensarPagoFactura (Compensacion compensacion);
}

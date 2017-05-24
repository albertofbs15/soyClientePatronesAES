package domain.Convenio;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Factura;
import domain.Convenio.entity.Pago;

/**
 * Created by AHernandezS on 3/05/2017.
 */
public class IntermediateRouting implements IntermediateRoutingService {

    @Override
    public Factura consultarFactura(String idFactura) {
        return null;
    }

    @Override
    public boolean pagoFactura(Pago pago) {
        return false;
    }

    @Override
    public boolean compensarPagoFactura(Compensacion compensacion) {
        return false;
    }
}

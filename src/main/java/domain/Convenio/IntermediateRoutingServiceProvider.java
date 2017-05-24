package domain.Convenio;

import domain.Convenio.entity.Compensacion;
import domain.Convenio.entity.Factura;
import domain.Convenio.entity.Pago;
import domain.Convenio.entity.Respuesta;
import servicios.dispatcher.DispatcherService;
import servicios.localizacion.LocalizacionService;
import servicios.transformacion.TransformacionService;

import java.util.concurrent.CompletableFuture;

/**
 * Created by AHernandezS on 3/05/2017.
 */
public class IntermediateRoutingServiceProvider implements IntermediateRoutingService {

    private final String EMPTY_PAYLOAD = "";
    private final LocalizacionService localizacionService;
    private final TransformacionService transformacionService;
    private final DispatcherService dispatcherService;

    public IntermediateRoutingServiceProvider(LocalizacionService localizacionService, TransformacionService transformacionService, DispatcherService dispatcherService) {
        this.localizacionService = localizacionService;
        this.transformacionService = transformacionService;
        this.dispatcherService = dispatcherService;
    }

    @Override
    public CompletableFuture<Respuesta> consultarFactura(String idFactura) {
        CompletableFuture<String> urlPromise = localizacionService.getEndpointDeConsulta(idFactura);
        return urlPromise.thenCompose( url -> {
            CompletableFuture<String> response = dispatcherService.sendRequest(url, EMPTY_PAYLOAD);
            return response.thenCompose(payload ->
            {
                return transformacionService.getResponseDeConsulta(idFactura, payload);
            });
        });
    }

    @Override
    public CompletableFuture<Respuesta> pagoFactura(Pago pago) {
        CompletableFuture<String> urlPromise = localizacionService.getEndpointDeConsulta(pago.getFactura().getReferenciaFactura());
        CompletableFuture<String> payloadPromise = transformacionService.getPayloadParaPagos(pago.getFactura().getReferenciaFactura(), pago);
        return CompletableFuture.allOf(urlPromise, payloadPromise).thenCompose( aVoid -> {
            String url = urlPromise.join();
            String payload = payloadPromise.join();
            CompletableFuture<String> response = dispatcherService.sendRequest(url, payload);
            return response.thenCompose(payloadResponse ->
            {
                return transformacionService.getResponseParaPagos(pago.getFactura().getReferenciaFactura(), payload);
            });
        });
    }

    @Override
    public CompletableFuture<Respuesta> compensarPagoFactura(Compensacion compensacion) {
        CompletableFuture<String> urlPromise = localizacionService.getEndpointDeConsulta(compensacion.getFactura().getReferenciaFactura());
        CompletableFuture<String> payloadPromise = transformacionService.getPayloadParaCompensacionDePagos(compensacion.getFactura().getReferenciaFactura(), compensacion);
        return CompletableFuture.allOf(urlPromise, payloadPromise).thenCompose( aVoid -> {
            String url = urlPromise.join();
            String payload = payloadPromise.join();
            CompletableFuture<String> response = dispatcherService.sendRequest(url, payload);
            return response.thenCompose(payloadResponse ->
            {
                return transformacionService.getResponseParaCompensacionDePagos(compensacion.getFactura().getReferenciaFactura(), payload);
            });
        });
    }
}

package domain.commands;

import java.time.LocalDate;

/**
 * Created by AHernandezS on 3/06/2017.
 */
public class Operacion {
    private String tipoOperacion;
    private String tipoServicion;
    private String destino;
    private String fechaInicial;
    private String fechaFinal;
    private String idReserva;

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getTipoServicion() {
        return tipoServicion;
    }

    public void setTipoServicion(String tipoServicion) {
        this.tipoServicion = tipoServicion;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(String fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }
}

package domain.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by AHernandezS on 2/06/2017.
 */
public class RespuestaConsultaDisponibilidadConID implements Serializable{

    private int id;
    private String nombreConvenio;
    private String tipoServivio;
    private long costoTotal;
    private List<DetalleServicio> detalles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreConvenio() {
        return nombreConvenio;
    }

    public void setNombreConvenio(String nombreConvenio) {
        this.nombreConvenio = nombreConvenio;
    }

    public String getTipoServivio() {
        return tipoServivio;
    }

    public void setTipoServivio(String tipoServivio) {
        this.tipoServivio = tipoServivio;
    }

    public long getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(long costoTotal) {
        this.costoTotal = costoTotal;
    }

    public List<DetalleServicio> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleServicio> detalles) {
        this.detalles = detalles;
    }
}

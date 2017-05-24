//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2017.05.23 a las 09:47:26 PM COT 
//


package domain.Convenio.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para factura complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="factura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="referenciaFactura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="totalPagar" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "factura", propOrder = {
    "referenciaFactura",
    "totalPagar"
})
public class Factura {

    @XmlElement(required = true)
    protected String referenciaFactura;
    protected double totalPagar;

    /**
     * Obtiene el valor de la propiedad referenciaFactura.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenciaFactura() {
        return referenciaFactura;
    }

    /**
     * Define el valor de la propiedad referenciaFactura.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenciaFactura(String value) {
        this.referenciaFactura = value;
    }

    /**
     * Obtiene el valor de la propiedad totalPagar.
     * 
     */
    public double getTotalPagar() {
        return totalPagar;
    }

    /**
     * Define el valor de la propiedad totalPagar.
     * 
     */
    public void setTotalPagar(double value) {
        this.totalPagar = value;
    }

}

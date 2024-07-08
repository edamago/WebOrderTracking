package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Proveedor;

import java.util.Date;

@Data
public class OrdenCompraRequest {
    private Integer id;
    private Integer t_proveedor_id;
    private Date fecha;
    private String tipo;
    private String moneda;
    private Double subtotal;
    private Double igv;
    private Double total;
    private String comentarios;
    private Boolean enviado;
    private String estado;
}

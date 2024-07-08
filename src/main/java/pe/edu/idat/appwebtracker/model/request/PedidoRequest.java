package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Cliente;

import java.util.Date;
@Data
public class PedidoRequest {
    private Integer id;
    private Integer t_cliente_id;
    private Date fecha;
    private String atencion;
    private String moneda;
    private Double subtotal;
    private Double igv;
    private Double total;
    private String comentario;
    private Boolean enviado;
    private String estado;
}

package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Cliente;

import java.util.Date;

@Data
public class CobranzaRequest {

    private Integer id;
    private Integer t_cliente_id;
    private Date fecha;
    private String moneda;
    private Double importe;
    private String forma_pago;
    private String documento;
    private String estado;
}

package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Comprobante;
import pe.edu.idat.appwebtracker.model.bd.Pedido;

@Data
public class DetalleComprobanteRequest {

    private Integer id;
    private Integer t_comprobante_id;
    private Integer t_pedido_id;
    private Integer importe;
    private String estado;
}

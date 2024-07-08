package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.OrdenCompra;
import pe.edu.idat.appwebtracker.model.bd.Producto;

@Data
public class DetalleOrdenCompraRequest {

    private Integer id;
    private Integer t_orden_compra_id;
    private Integer t_detalle_pedido_id;
    private Integer t_producto_id;
    private Integer cantidad;
    private Double precio;
    private String comentarios;
    private String estado;
}

package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.bd.Producto;

@Data
public class DetallePedidoRequest {
    private Integer id;
    private Integer t_pedido_id;
    private Integer t_producto_id;
    private Integer cantidad;
    private Double precio;
    private String comentarios;
    private String estado;
}

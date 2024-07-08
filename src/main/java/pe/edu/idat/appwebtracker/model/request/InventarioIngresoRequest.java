package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.*;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.DetalleOrdenCompra;
import pe.edu.idat.appwebtracker.model.bd.Inventario;
import pe.edu.idat.appwebtracker.model.bd.Producto;

@Data
public class InventarioIngresoRequest {
    private Integer id;
    private Integer t_inventario_id;
    private Integer t_detalle_orden_compra_id;
    private Integer producto_id;
    private Integer cantidad;
    private String comentarios;
    private Boolean recepcionado;
    private String estado;
}

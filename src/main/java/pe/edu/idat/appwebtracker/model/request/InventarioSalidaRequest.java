package pe.edu.idat.appwebtracker.model.request;

import lombok.Data;

@Data
public class InventarioSalidaRequest {
    private Integer id;
    private Integer t_inventario_id;
    private Integer t_detalle_pedido_id;
    private Integer producto_id;
    private Integer cantidad;
    private String comentarios;
    private Boolean entregado;
    private String estado;
}

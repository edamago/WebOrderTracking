package pe.edu.idat.appwebtracker.model.request;

import lombok.Data;

@Data
public class EstadoDetallePedidoRequest {
    private Integer id;
    private String fecha;
    private String comentario;
    private Integer enviado;
    private Integer t_estado_id;
    private Integer t_detalle_pedido_id;

}

package pe.edu.idat.appwebtracker.model.request;

import lombok.Data;

@Data
public class NotificacionRequest {
    private Integer id;
    private String descripcion;
    private String estado;
    private Integer visto;
    private Integer t_usuario_id;
}

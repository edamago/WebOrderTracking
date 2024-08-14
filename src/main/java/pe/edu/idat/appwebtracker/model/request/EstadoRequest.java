package pe.edu.idat.appwebtracker.model.request;

import lombok.Data;

@Data
public class EstadoRequest {
    private Integer id;
    private String descripcion;
    private String estado;
    private Integer orden;
    private Integer activo;
}

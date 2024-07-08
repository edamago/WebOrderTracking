package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProveedorRequest {
    private Integer id;
    private String tipo_documento;
    private String documento;
    private String nombre;
    private String direccion;
    private String distrito;
    private String ciudad;
    private String tipo;
    private String comentarios;
    private String estado;
}

package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteRequest {

    private Integer id;
    private String tipo_documento;
    private String documento;
    private String nombre;
    private String direccion;
    private String direccion_entrega;
    private String distrito;
    private String ciudad;
    private String tipo;
    private String clasif_comercial;
    private String comentarios;
    private String estado;
}

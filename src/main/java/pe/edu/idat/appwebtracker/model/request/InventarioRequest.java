package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class InventarioRequest {

    private Integer id;
    private String tipo;
    private String numero;
    private String motivo;
    private Date fecha;
    private String comentarios;
    private String direccion;
    private String estado;
}

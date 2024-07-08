package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequest {
    private Integer id;
    @NotNull
    @NotBlank(message = "Ingresar el nombre del usuario")
    private String nombre;
    @NotNull
    @NotBlank(message = "Ingresar el correo del usuario")
    private String correo;
    private String estado;
    private String password;
    private Boolean activo;
    private String nomusuario;
}

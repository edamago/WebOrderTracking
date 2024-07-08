package pe.edu.idat.appwebtracker.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ResultadoResponse {
    private Boolean respuesta;
    private String mensaje;



}
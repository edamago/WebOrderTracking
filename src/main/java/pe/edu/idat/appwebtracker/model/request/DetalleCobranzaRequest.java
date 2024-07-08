package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pe.edu.idat.appwebtracker.model.bd.Cobranza;
import pe.edu.idat.appwebtracker.model.bd.Comprobante;

@Data
public class DetalleCobranzaRequest {

    private Integer id;
    private Integer t_cobranza_id;
    private Integer t_comprobante_id;
    private Double importe_cobro;
    private String comentarios;
    private String estado;

}

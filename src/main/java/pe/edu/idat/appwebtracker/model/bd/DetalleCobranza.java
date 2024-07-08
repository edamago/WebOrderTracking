package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_detalle_cobranza")
public class DetalleCobranza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_cobranza_id")
    private Cobranza cobranza;

    @ManyToOne
    @JoinColumn(name = "t_comprobante_id")
    private Comprobante comprobante;

    @Column(name="importe_cobro")
    private Double importe_cobro;
    @Column(name="comentarios")
    private String comentarios;
    @Column(name="estado")
    private String estado;
}

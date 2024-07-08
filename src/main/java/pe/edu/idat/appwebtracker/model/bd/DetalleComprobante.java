package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_detalle_comprobante")
public class DetalleComprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_comprobante_id")
    private Comprobante comprobante;

    @ManyToOne
    @JoinColumn(name = "t_pedido_id")
    private Pedido pedido;

    @Column(name="importe")
    private Integer importe;
    @Column(name="estado")
    private String estado;

}

package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="t_estado_pedido")
public class EstadoDetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "t_detalle_pedido_id")
    private DetallePedido detallePedido;

    @Column(name="fecha")
    private String fecha;
    @Column(name="comentario")
    private String comentario;
    @Column(name="enviado")
    private Integer enviado;

    //@Column(name="t_estado_id")
    //private Integer t_estado_id;
    //@Column(name="t_detalle_pedido_id")
    //private Integer t_detalle_pedido_id;

}

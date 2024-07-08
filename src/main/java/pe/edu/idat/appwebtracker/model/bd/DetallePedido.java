package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="t_detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "t_producto_id")
    private Producto producto;

    @Column(name="cantidad")
    private Integer cantidad;
    @Column(name="precio")
    private Double precio;
    @Column(name="comentarios")
    private String comentarios;
    @Column(name="estado")
    private String estado;
}
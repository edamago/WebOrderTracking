package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_detalle_orden_compra")
public class DetalleOrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_orden_compra_id")
    private OrdenCompra ordenCompra;

    @ManyToOne
    @JoinColumn(name = "t_detalle_pedido_id")
    private DetallePedido detallePedido;

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

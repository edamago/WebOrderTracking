package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_inventario_salida")
public class InventarioSalida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_inventario_id")
    private Inventario t_inventario;

    @ManyToOne
    @JoinColumn(name = "t_detalle_pedido_id")
    private DetallePedido t_detalle_pedido;

    @ManyToOne
    @JoinColumn(name = "t_producto_id")
    private Producto producto;

    @Column(name="cantidad")
    private Integer cantidad;
    @Column(name="comentarios")
    private String comentarios;
    @Column(name="entregado")
    private Boolean entregado;
    @Column(name="estado")
    private String estado;
}

package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_inventario_ingreso")
public class InventarioIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_inventario_id")
    private Inventario t_inventario;

    @ManyToOne
    @JoinColumn(name = "t_detalle_orden_compra_id")
    private DetalleOrdenCompra t_detalle_orden_compra;

    @ManyToOne
    @JoinColumn(name = "t_producto_id")
    private Producto producto;

    @Column(name="cantidad")
    private Integer cantidad;
    @Column(name="comentarios")
    private String comentarios;
    @Column(name="recepcionado")
    private Boolean recepcionado;
    @Column(name="estado")
    private String estado;
}

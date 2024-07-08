package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_orden_compra")
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_proveedor_id")
    private Proveedor proveedor;

    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "igv")
    private Double igv;
    @Column(name = "total")
    private Double total;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "enviado")
    private Boolean enviado;
    @Column(name = "estado")
    private String estado;
}

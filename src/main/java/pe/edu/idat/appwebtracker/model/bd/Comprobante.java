package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_comprobante")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_cliente_id")
    private Cliente cliente;

    @Column(name = "tipo")
    private String tipo;
    @Column(name = "numero")
    private String numero;
    @Column(name = "fecha")
    private Date fecha;
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
    @Column(name = "estado")
    private String estado;
}

package pe.edu.idat.appwebtracker.model.bd;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "t_pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_cliente_id")
    private Cliente cliente;

    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "atencion")
    private String atencion;
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "subtotal")
    private Double subtotal;
    @Column(name = "igv")
    private Double igv;
    @Column(name = "total")
    private Double total;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "enviado")
    private Boolean enviado;
    @Column(name = "estado")
    private String estado;
}

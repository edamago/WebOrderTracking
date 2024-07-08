package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_cobranza")
public class Cobranza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_cliente_id")
    private Cliente cliente;

    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "moneda")
    private String moneda;
    @Column(name = "importe")
    private Double importe;
    @Column(name = "forma_pago")
    private String forma_pago;
    @Column(name = "documento")
    private String documento;
    @Column(name = "estado")
    private String estado;

}

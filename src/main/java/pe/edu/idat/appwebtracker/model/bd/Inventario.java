package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo")
    private String tipo;
    @Column(name = "numero")
    private String numero;
    @Column(name = "motivo")
    private String motivo;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "estado")
    private String estado;

}

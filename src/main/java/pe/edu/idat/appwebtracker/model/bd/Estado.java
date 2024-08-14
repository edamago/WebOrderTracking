package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="t_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="descripcion")
    private String descripcion;
    @Column(name="estado")
    private String estado;
    @Column(name="orden")
    private Integer orden;
    @Column(name="activo")
    private Integer activo;


}

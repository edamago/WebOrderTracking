package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="t_notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "t_usuario_id")
    private Usuario usuario;

    @Column(name="descripcion")
    private String descripcion;
    @Column(name="estado")
    private String estado;
    @Column(name="visto")
    private Integer visto;

}

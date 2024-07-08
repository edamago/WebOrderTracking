package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name="t_cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "tipo_documento")
    private String tipo_documento;
    @Column(name = "documento")
    private String documento;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "direccion_entrega")
    private String direccion_entrega;
    @Column(name = "distrito")
    private String distrito;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "clasif_comercial")
    private String clasif_comercial;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "estado")
    private String estado;
}

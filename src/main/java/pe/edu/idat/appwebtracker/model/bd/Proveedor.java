package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name="t_proveedor")
public class Proveedor {
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
    @Column(name = "distrito")
    private String distrito;
    @Column(name = "ciudad")
    private String ciudad;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "estado")
    private String estado;
}

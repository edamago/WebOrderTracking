package pe.edu.idat.appwebtracker.model.bd;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "correo")
    private String correo;
    @Column(name = "estado")
    private String estado;
    @Column(name = "password")
    private String password;
    @Column(name = "activo")
    private Boolean activo;
    @Column(name = "nomusuario")
    private String nomusuario;
    @Column(name = "dni")
    private String dni;
    @Column(name = "ap_paterno")
    private String ap_paterno;
    @Column(name = "ap_materno")
    private String ap_materno;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "celular")
    private String celular;

    @ManyToOne
    @JoinColumn(name = "t_cliente_id")
    private Cliente cliente;


    @ManyToMany(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "idusuario"),
            inverseJoinColumns = @JoinColumn(name = "idrol"))
    private Set<Rol> roles;

}

package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

    Usuario findBynomusuario(String nombre);

    Usuario findBycorreo(String correo);
}

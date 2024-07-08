package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol,Integer> {

    Rol findBynomrol(String nomrol);
}

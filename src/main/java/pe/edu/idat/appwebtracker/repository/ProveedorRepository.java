package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor,Integer> {
}

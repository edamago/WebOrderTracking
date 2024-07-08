package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Inventario;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {
    List<Inventario>findBytipo(String tipo);
}

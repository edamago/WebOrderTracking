package pe.edu.idat.appwebtracker.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Cliente;


@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}

package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Comprobante;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante,Integer> {
}

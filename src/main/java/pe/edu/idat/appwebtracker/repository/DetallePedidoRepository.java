package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido,Integer> {
}

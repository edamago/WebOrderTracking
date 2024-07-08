package pe.edu.idat.appwebtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.idat.appwebtracker.model.bd.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
}

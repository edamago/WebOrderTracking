package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.repository.DetallePedidoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class DetallePedidoService {
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedido> listarDetallePedidos(){return detallePedidoRepository.findAll();}
}

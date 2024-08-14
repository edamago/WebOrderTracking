package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.repository.EstadoDetallePedidoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EstadoDetallePedidoService {
    private EstadoDetallePedidoRepository estadoDetallePedidoRepository;

    public List<EstadoDetallePedido>listarEstadoDetallePedidos(){return estadoDetallePedidoRepository.findAll();}
}

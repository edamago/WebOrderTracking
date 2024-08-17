package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.Estado;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.model.request.ClienteRequest;
import pe.edu.idat.appwebtracker.model.request.EstadoDetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.EstadoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.EstadoDetallePedidoRepository;
import pe.edu.idat.appwebtracker.repository.EstadoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EstadoService {
    private EstadoRepository estadoRepository;

    public List<Estado>listarEstados(){return estadoRepository.findAll();}


}

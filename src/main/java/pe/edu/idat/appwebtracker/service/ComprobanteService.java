package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.appwebtracker.model.bd.*;
import pe.edu.idat.appwebtracker.model.request.ComprobanteRequest;
import pe.edu.idat.appwebtracker.model.request.DetalleComprobanteRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.ComprobanteRepository;
import pe.edu.idat.appwebtracker.repository.DetalleComprobanteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ComprobanteService {
    private ComprobanteRepository comprobanteRepository;
    private DetalleComprobanteRepository detalleComprobanteRepository;

    public List<Comprobante> listarComprobantes(){return comprobanteRepository.findAll();}
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarComprobante(ComprobanteRequest comprobanteRequest,
                                                  List<DetalleComprobanteRequest> detalleComprobanteRequestList) {
        String mensaje="Comprobante registrado correctamente";
        Boolean respuesta=true;
        try {
            Comprobante comprobante = new Comprobante();
            comprobante.setTipo(comprobanteRequest.getTipo());
            comprobante.setNumero(comprobanteRequest.getNumero());
            comprobante.setFecha(comprobanteRequest.getFecha());
            comprobante.setMoneda(comprobanteRequest.getMoneda());
            comprobante.setSubtotal(comprobanteRequest.getSubtotal());
            comprobante.setIgv(comprobanteRequest.getIgv());
            comprobante.setTotal(comprobanteRequest.getTotal());
            comprobante.setComentarios(comprobanteRequest.getComentarios());
            comprobante.setEstado(comprobanteRequest.getEstado());
            Cliente cliente = new Cliente();
            cliente.setId(comprobanteRequest.getT_cliente_id());
            comprobante.setCliente(cliente);

            Comprobante insertcomprobante = comprobanteRepository.save(comprobante);

            for(DetalleComprobanteRequest detalleComprobanteRequest : detalleComprobanteRequestList){
                DetalleComprobante detalleComprobante = new DetalleComprobante();

                detalleComprobante.setId(detalleComprobanteRequest.getId());
                detalleComprobante.setImporte(detalleComprobanteRequest.getImporte());
                detalleComprobante.setEstado(detalleComprobanteRequest.getEstado());

                Pedido pedido=new Pedido();
                pedido.setId(detalleComprobanteRequest.getT_pedido_id());
                detalleComprobante.setPedido(pedido);

                detalleComprobante.setComprobante(insertcomprobante);

                detalleComprobanteRepository.save(detalleComprobante);
            }

        } catch (Exception ex) {
            mensaje="Comprobante no registrado";
            respuesta = false;

        }
        return null;
    }

}

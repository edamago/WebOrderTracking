package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.bd.Producto;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.DetallePedidoRepository;
import pe.edu.idat.appwebtracker.repository.PedidoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class PedidoService {
    private PedidoRepository pedidoRepository;
    private DetallePedidoRepository detallePedidoRepository;

    public List<Pedido>listarPedidos(){return pedidoRepository.findAll();}
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarPedido(PedidoRequest pedidoRequest,
                                            List<DetallePedidoRequest> detallePedidoRequestList) {
        String mensaje="Pedido registrado correctamente";
        Boolean respuesta=true;
        try {
            Pedido pedido = new Pedido();
            pedido.setFecha(pedidoRequest.getFecha());
            pedido.setAtencion(pedidoRequest.getAtencion());
            pedido.setMoneda(pedidoRequest.getMoneda());
            pedido.setSubtotal(pedidoRequest.getSubtotal());
            pedido.setIgv(pedidoRequest.getIgv());
            pedido.setTotal(pedidoRequest.getTotal());
            pedido.setComentario(pedidoRequest.getComentario());
            pedido.setEnviado(pedidoRequest.getEnviado());
            pedido.setEstado(pedidoRequest.getEstado());
            Cliente cliente = new Cliente();
            cliente.setId(pedidoRequest.getT_cliente_id());
            pedido.setCliente(cliente);

            Pedido insertpedido = pedidoRepository.save(pedido);

            for(DetallePedidoRequest detallePedidoRequest : detallePedidoRequestList){
                DetallePedido detallePedido = new DetallePedido();

                detallePedido.setId(detallePedidoRequest.getId());
                detallePedido.setCantidad(detallePedidoRequest.getCantidad());
                detallePedido.setPrecio(detallePedidoRequest.getPrecio());
                detallePedido.setComentarios(detallePedidoRequest.getComentarios());
                detallePedido.setEstado(detallePedidoRequest.getEstado());
                Producto producto=new Producto();
                producto.setId(detallePedidoRequest.getT_producto_id());
                detallePedido.setProducto(producto);

                detallePedido.setPedido(insertpedido);

                detallePedidoRepository.save(detallePedido);
            }

        } catch (Exception ex) {
            mensaje="Pedido no registrado";
            respuesta = false;

        }
        return null;
    }
}

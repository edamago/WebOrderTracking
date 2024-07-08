package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.appwebtracker.model.bd.*;
import pe.edu.idat.appwebtracker.model.request.DetalleOrdenCompraRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.OrdenCompraRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.DetalleOrdenCompraRepository;
import pe.edu.idat.appwebtracker.repository.OrdenCompraRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class OrdenCompraService {
    private OrdenCompraRepository ordenCompraRepository;
    private DetalleOrdenCompraRepository detalleOrdenCompraRepository;

    public List<OrdenCompra> listarOrdenesCompra(){return ordenCompraRepository.findAll();}

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarOrdenCompra(OrdenCompraRequest ordenCompraRequest,
                                                  List<DetalleOrdenCompraRequest> detalleOrdenCompraRequestList) {
        String mensaje="Orden de compra registrada correctamente";
        Boolean respuesta=true;
        try {
            OrdenCompra ordenCompra = new OrdenCompra();
            ordenCompra.setFecha(ordenCompraRequest.getFecha());
            ordenCompra.setTipo(ordenCompraRequest.getTipo());
            ordenCompra.setMoneda(ordenCompraRequest.getMoneda());
            ordenCompra.setSubtotal(ordenCompraRequest.getSubtotal());
            ordenCompra.setIgv(ordenCompraRequest.getIgv());
            ordenCompra.setTotal(ordenCompraRequest.getTotal());
            ordenCompra.setComentarios(ordenCompraRequest.getComentarios());
            ordenCompra.setEnviado(ordenCompraRequest.getEnviado());
            ordenCompra.setEstado(ordenCompraRequest.getEstado());
            Proveedor proveedor = new Proveedor();
            proveedor.setId(ordenCompraRequest.getT_proveedor_id());
            ordenCompra.setProveedor(proveedor);

            OrdenCompra insertordencompra = ordenCompraRepository.save(ordenCompra);

            for(DetalleOrdenCompraRequest detalleOrdenCompraRequest : detalleOrdenCompraRequestList){
                DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();

                detalleOrdenCompra.setId(detalleOrdenCompraRequest.getId());
                detalleOrdenCompra.setCantidad(detalleOrdenCompraRequest.getCantidad());
                detalleOrdenCompra.setPrecio(detalleOrdenCompraRequest.getPrecio());
                detalleOrdenCompra.setComentarios(detalleOrdenCompraRequest.getComentarios());
                detalleOrdenCompra.setEstado(detalleOrdenCompraRequest.getEstado());

                Producto producto=new Producto();
                producto.setId(detalleOrdenCompraRequest.getT_producto_id());
                detalleOrdenCompra.setProducto(producto);

                DetallePedido detallePedido=new DetallePedido();
                detallePedido.setId(detalleOrdenCompraRequest.getT_detalle_pedido_id());
                detalleOrdenCompra.setDetallePedido(detallePedido);

                detalleOrdenCompra.setOrdenCompra(insertordencompra);

                detalleOrdenCompraRepository.save(detalleOrdenCompra);
            }

        } catch (Exception ex) {
            mensaje="Orden de compra no registrada";
            respuesta = false;
        }
        return null;
    }
}

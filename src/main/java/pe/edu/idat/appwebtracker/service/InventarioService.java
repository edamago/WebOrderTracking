package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.appwebtracker.model.bd.*;
import pe.edu.idat.appwebtracker.model.request.*;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.InventarioIngresoRepository;
import pe.edu.idat.appwebtracker.repository.InventarioRepository;
import pe.edu.idat.appwebtracker.repository.InventarioSalidaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class InventarioService {
    private InventarioRepository inventarioRepository;
    private InventarioIngresoRepository inventarioIngresoRepository;
    private InventarioSalidaRepository inventarioSalidaRepository;

    public List<Inventario>listarInventarios(){return inventarioRepository.findAll();}

    public List<Inventario>listarInventariosPorTipo(String  tipo){return inventarioRepository.findBytipo(tipo);}

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarInventarioIngreso(InventarioRequest inventarioRequest,
                                                        List<InventarioIngresoRequest> inventarioIngresoRequestList) {
        try {
            Inventario inventario = new Inventario();
            inventario.setTipo(inventarioRequest.getTipo());
            inventario.setNumero(inventarioRequest.getNumero());
            inventario.setMotivo(inventarioRequest.getMotivo());
            inventario.setFecha(inventarioRequest.getFecha());
            inventario.setComentarios(inventarioRequest.getComentarios());
            inventario.setDireccion(inventarioRequest.getDireccion());
            inventario.setEstado(inventarioRequest.getEstado());

            Inventario insertinventario = inventarioRepository.save(inventario);

            for(InventarioIngresoRequest inventarioIngresoRequest : inventarioIngresoRequestList){
                InventarioIngreso inventarioIngreso = new InventarioIngreso();

                inventarioIngreso.setId(inventarioIngresoRequest.getId());
                inventarioIngreso.setCantidad(inventarioIngresoRequest.getCantidad());
                inventarioIngreso.setComentarios(inventarioIngresoRequest.getComentarios());
                inventarioIngreso.setRecepcionado(inventarioIngresoRequest.getRecepcionado());
                inventarioIngreso.setEstado(inventarioIngresoRequest.getEstado());

                DetalleOrdenCompra detalleOrdenCompra=new DetalleOrdenCompra();
                detalleOrdenCompra.setId(inventarioIngresoRequest.getT_detalle_orden_compra_id());
                inventarioIngreso.setT_detalle_orden_compra(detalleOrdenCompra);

                Producto producto=new Producto();
                producto.setId(inventarioIngresoRequest.getProducto_id());
                inventarioIngreso.setProducto(producto);

                inventarioIngreso.setT_inventario(insertinventario);

                inventarioIngresoRepository.save(inventarioIngreso);
            }

        } catch (Exception ex) {

        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarInventarioSalida(InventarioRequest inventarioRequest,
                                                        List<InventarioSalidaRequest> inventarioSalidaRequestList) {
        String mensaje="Documento registrado correctamente";
        Boolean respuesta=true;
        try {
            Inventario inventario = new Inventario();
            inventario.setTipo(inventarioRequest.getTipo());
            inventario.setNumero(inventarioRequest.getNumero());
            inventario.setMotivo(inventarioRequest.getMotivo());
            inventario.setFecha(inventarioRequest.getFecha());
            inventario.setComentarios(inventarioRequest.getComentarios());
            inventario.setDireccion(inventarioRequest.getDireccion());
            inventario.setEstado(inventarioRequest.getEstado());

            Inventario insertinventario = inventarioRepository.save(inventario);

            for(InventarioSalidaRequest inventarioSalidaRequest : inventarioSalidaRequestList){
                InventarioSalida inventarioSalida = new InventarioSalida();

                inventarioSalida.setId(inventarioSalidaRequest.getId());
                inventarioSalida.setCantidad(inventarioSalidaRequest.getCantidad());
                inventarioSalida.setComentarios(inventarioSalidaRequest.getComentarios());
                inventarioSalida.setEntregado(inventarioSalidaRequest.getEntregado());
                inventarioSalida.setEstado(inventarioSalidaRequest.getEstado());

                DetallePedido detallePedido=new DetallePedido();
                detallePedido.setId(inventarioSalidaRequest.getT_detalle_pedido_id());
                inventarioSalida.setT_detalle_pedido(detallePedido);

                Producto producto=new Producto();
                producto.setId(inventarioSalidaRequest.getProducto_id());
                inventarioSalida.setProducto(producto);

                inventarioSalida.setT_inventario(insertinventario);

                inventarioSalidaRepository.save(inventarioSalida);
            }

        } catch (Exception ex) {
            mensaje="Documento no registrado";
            respuesta = false;

        }
        return null;
    }

}

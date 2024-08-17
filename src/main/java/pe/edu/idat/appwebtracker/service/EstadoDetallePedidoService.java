package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.Estado;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.model.request.EstadoDetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.EstadoDetallePedidoRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class EstadoDetallePedidoService {
    private EstadoDetallePedidoRepository estadoDetallePedidoRepository;

    public List<EstadoDetallePedido>listarEstadoDetallePedidos(){return estadoDetallePedidoRepository.findAll();}

    public ResultadoResponse guardarEstadoDetallePedido(EstadoDetallePedidoRequest estadoDetallePedidoRequest){
        String mensaje="Estado de posición registrado correctamente";
        Boolean respuesta=true;
        try{
            EstadoDetallePedido estadoDetallePedido = new EstadoDetallePedido();
            if(estadoDetallePedidoRequest.getId()>0){
                estadoDetallePedido.setId(estadoDetallePedidoRequest.getId());
            }
            estadoDetallePedido.setFecha(estadoDetallePedidoRequest.getFecha());
            estadoDetallePedido.setComentario(estadoDetallePedidoRequest.getComentario());
            estadoDetallePedido.setEnviado(estadoDetallePedidoRequest.getEnviado());

            Estado estado = new Estado();
            estado.setId(estadoDetallePedidoRequest.getT_estado_id());
            estadoDetallePedido.setEstado(estado);

            DetallePedido detallePedido = new DetallePedido();
            detallePedido.setId(estadoDetallePedidoRequest.getT_detalle_pedido_id());
            estadoDetallePedido.setDetallePedido(detallePedido);



            estadoDetallePedidoRepository.save(estadoDetallePedido);
        }catch (Exception ex){
            mensaje="Estado de posición no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}

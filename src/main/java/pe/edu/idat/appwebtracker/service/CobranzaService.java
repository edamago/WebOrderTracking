package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.idat.appwebtracker.model.bd.*;
import pe.edu.idat.appwebtracker.model.request.CobranzaRequest;
import pe.edu.idat.appwebtracker.model.request.DetalleCobranzaRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.CobranzaRepository;
import pe.edu.idat.appwebtracker.repository.DetalleCobranzaRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class CobranzaService {
    private CobranzaRepository cobranzaRepository;
    private DetalleCobranzaRepository detalleCobranzaRepository;

    public List<Cobranza> listarCobranzas(){return cobranzaRepository.findAll();}
    @Transactional(propagation = Propagation.REQUIRED)
    public ResultadoResponse registrarCobranza(CobranzaRequest cobranzaRequest,
                                               List<DetalleCobranzaRequest> detalleCobranzaRequestList) {
        String mensaje="Cobranza registrada correctamente";
        Boolean respuesta=true;
        try {
            Cobranza cobranza = new Cobranza();
            cobranza.setFecha(cobranzaRequest.getFecha());
            cobranza.setMoneda(cobranzaRequest.getMoneda());
            cobranza.setImporte(cobranzaRequest.getImporte());
            cobranza.setForma_pago(cobranzaRequest.getForma_pago());
            cobranza.setDocumento(cobranzaRequest.getDocumento());
            cobranza.setEstado(cobranzaRequest.getEstado());
            Cliente cliente = new Cliente();
            cliente.setId(cobranzaRequest.getT_cliente_id());
            cobranza.setCliente(cliente);

            Cobranza insertcobranza = cobranzaRepository.save(cobranza);

            for(DetalleCobranzaRequest detalleCobranzaRequest : detalleCobranzaRequestList){
                DetalleCobranza detalleCobranza = new DetalleCobranza();

                detalleCobranza.setId(detalleCobranzaRequest.getId());
                detalleCobranza.setImporte_cobro(detalleCobranzaRequest.getImporte_cobro());
                detalleCobranza.setComentarios(detalleCobranzaRequest.getComentarios());
                detalleCobranza.setEstado(detalleCobranzaRequest.getEstado());

                Comprobante comprobante=new Comprobante();
                comprobante.setId(detalleCobranzaRequest.getT_cobranza_id());
                detalleCobranza.setComprobante(comprobante);

                detalleCobranza.setCobranza(insertcobranza);

                detalleCobranzaRepository.save(detalleCobranza);
            }

        } catch (Exception ex) {
            mensaje="Cobranza no registrada";
            respuesta = false;
        }
        return null;
    }

}

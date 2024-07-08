package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Comprobante;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.request.ComprobanteRequest;
import pe.edu.idat.appwebtracker.model.request.DetalleComprobanteRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.ComprobanteService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/comprobante")
public class ComprobanteController {
    private ComprobanteService comprobanteService;

    @GetMapping("")
    public String frmMantComprobante(Model model){
        model.addAttribute("listacomprobantes",comprobanteService.listarComprobantes());
        return "backoffice/comprobante/frmcomprobante";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Comprobante> listarPedidos(){
        return comprobanteService.listarComprobantes();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarComprobante(@RequestBody ComprobanteRequest comprobanteRequest, List<DetalleComprobanteRequest> detalleComprobanteRequestList){

        return comprobanteService.registrarComprobante(comprobanteRequest,detalleComprobanteRequestList);
    }
}

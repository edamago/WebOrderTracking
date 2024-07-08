package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Cobranza;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.request.CobranzaRequest;
import pe.edu.idat.appwebtracker.model.request.DetalleCobranzaRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.CobranzaRepository;
import pe.edu.idat.appwebtracker.service.CobranzaService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/cobranza")
public class CobranzaController {
    private CobranzaService cobranzaService;

    @GetMapping("")
    public String frmMantPedido(Model model){
        model.addAttribute("listacobranzas",cobranzaService.listarCobranzas());
        return "backoffice/cobranza/frmcobranza";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Cobranza> listarCobranzas(){
        return cobranzaService.listarCobranzas();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarCobranza(@RequestBody CobranzaRequest cobranzaRequest, List<DetalleCobranzaRequest> detalleCobranzaRequestList){

        return cobranzaService.registrarCobranza(cobranzaRequest,detalleCobranzaRequestList);
    }



}

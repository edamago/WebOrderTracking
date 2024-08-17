package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Estado;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.model.request.ClienteRequest;
import pe.edu.idat.appwebtracker.model.request.EstadoDetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.EstadoDetallePedidoService;
import pe.edu.idat.appwebtracker.service.PedidoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/seguimiento")
public class EstadoDetallePedidoController {
    private EstadoDetallePedidoService estadoDetallePedidoService;
    @GetMapping("")
    public String frmMantSeguimiento(Model model){
        model.addAttribute("listarseguimiento",estadoDetallePedidoService.listarEstadoDetallePedidos());
        return "backoffice/seguimiento/frmseguimiento";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<EstadoDetallePedido> listarEstadosPosicionesPedido(){return estadoDetallePedidoService.listarEstadoDetallePedidos() ;}

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarEstadoDetallePedido(@RequestBody EstadoDetallePedidoRequest estadoDetallePedidoRequest){
        return estadoDetallePedidoService.guardarEstadoDetallePedido(estadoDetallePedidoRequest);
    }
}

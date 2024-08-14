package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appwebtracker.service.EstadoDetallePedidoService;
import pe.edu.idat.appwebtracker.service.PedidoService;

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
}

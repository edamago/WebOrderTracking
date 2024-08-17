package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.idat.appwebtracker.model.bd.DetallePedido;
import pe.edu.idat.appwebtracker.model.bd.EstadoDetallePedido;
import pe.edu.idat.appwebtracker.service.DetallePedidoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/posiciones")
public class DetallePedidoController {

    private DetallePedidoService detallePedidoService;
    @GetMapping("/listar")
    @ResponseBody
    public List<DetallePedido> listarDetallePedidos(){return detallePedidoService.listarDetallePedidos();}

}

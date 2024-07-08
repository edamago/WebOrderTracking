package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.OrdenCompra;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.request.DetalleOrdenCompraRequest;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.OrdenCompraRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.OrdenCompraService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/ordencompra")
public class OrdenCompraController {
    private OrdenCompraService ordenCompraService;

    @GetMapping("")
    public String frmMantOrdenCompra(Model model){
        model.addAttribute("listaordenescompra",ordenCompraService.listarOrdenesCompra());
        return "backoffice/ordencompra/frmordencompra";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<OrdenCompra> listarOrdenesCompra(){
        return ordenCompraService.listarOrdenesCompra();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarOrdenCompra(@RequestBody OrdenCompraRequest ordenCompraRequest, List<DetalleOrdenCompraRequest> detalleOrdenCompraRequestList){

        return ordenCompraService.registrarOrdenCompra(ordenCompraRequest,detalleOrdenCompraRequestList);
    }

}

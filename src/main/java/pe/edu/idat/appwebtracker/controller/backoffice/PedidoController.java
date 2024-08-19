package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.bd.Producto;
import pe.edu.idat.appwebtracker.model.request.DetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.PedidoRequest;
import pe.edu.idat.appwebtracker.model.request.ProductoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.PedidoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/pedido")
public class PedidoController {
    private PedidoService pedidoService;

    @GetMapping("")
    public String frmMantPedido(Model model){
        model.addAttribute("listapedidos",pedidoService.listarPedidos());
        return "backoffice/pedido/frmpedido";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Pedido>listarPedidos(){
        return pedidoService.listarPedidos();
    }


    //public ResultadoResponse guardarPedido(@RequestBody PedidoRequest pedidoRequest,List<DetallePedidoRequest> detallePedidoRequestList){

    //    return pedidoService.registrarPedido(pedidoRequest,detallePedidoRequestList);
    //}
    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarPedido(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.registrarPedido(pedidoRequest);
    }


}

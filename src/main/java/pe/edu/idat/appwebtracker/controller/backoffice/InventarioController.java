package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Inventario;
import pe.edu.idat.appwebtracker.model.bd.Pedido;
import pe.edu.idat.appwebtracker.model.request.*;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.InventarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/inventario")
public class InventarioController {
    private InventarioService inventarioService;

    @GetMapping("")
    public String frmMantInventario(Model model){
        model.addAttribute("listainventarios",inventarioService.listarInventarios());
        return "backoffice/inventario/frminventario";
    }

    @GetMapping("/ingreso")
    public String frmMantInventarioIngreso(Model model){
        model.addAttribute("listainventarios",inventarioService.listarInventariosPorTipo("INGRESO"));
        return "backoffice/inventario/frminventario";
    }

    @GetMapping("/salida")
    public String frmMantInventarioSalida(Model model){
        model.addAttribute("listainventarios",inventarioService.listarInventariosPorTipo("SALIDA"));
        return "backoffice/inventario/frminventario";
    }

    @PostMapping("/guardaringreso")
    @ResponseBody
    public ResultadoResponse guardarInventarioIngreso(@RequestBody InventarioRequest inventarioRequest, List<InventarioIngresoRequest> inventarioIngresoRequestList){

        return inventarioService.registrarInventarioIngreso(inventarioRequest,inventarioIngresoRequestList);
    }

    @PostMapping("/guardarsalida")
    @ResponseBody
    public ResultadoResponse guardarInventarioSalida(@RequestBody InventarioRequest inventarioRequest, List<InventarioSalidaRequest> inventarioSalidaRequestList){

        return inventarioService.registrarInventarioSalida(inventarioRequest,inventarioSalidaRequestList);
    }

}

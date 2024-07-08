package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Proveedor;
import pe.edu.idat.appwebtracker.model.request.ProveedorRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.ProveedorService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/proveedor")
public class ProveedorController {
    private ProveedorService proveedorService;

    @GetMapping("")
    public String frmMantProveedor(Model model){
        model.addAttribute("listarproveedores",proveedorService.listarProveedores());
        return "backoffice/proveedor/frmproveedor";
    }

    @GetMapping("listar")
    @ResponseBody
    public List<Proveedor>listarProveedores(){
        return proveedorService.listarProveedores();
    }

    @PostMapping("guardar")
    @ResponseBody
    public ResultadoResponse guardarProveedor(@RequestBody ProveedorRequest proveedorRequest){
        return proveedorService.guardarProveedor(proveedorRequest);
    }
}

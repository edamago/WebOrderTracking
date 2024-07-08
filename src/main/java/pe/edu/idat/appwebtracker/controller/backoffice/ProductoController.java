package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Producto;
import pe.edu.idat.appwebtracker.model.request.ProductoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.ProductoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/producto")
public class ProductoController {
    private ProductoService productoService;

    @GetMapping("")
    public String frmMantProducto(Model model){
        model.addAttribute("listaproductos",productoService.listarProductos());
        return "backoffice/producto/frmproducto";
    }

    @GetMapping("/listar")
    @ResponseBody
    public List<Producto>listarProductos(){
        return productoService.listarProductos();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarProducto(@RequestBody ProductoRequest productoRequest){

        return productoService.guardarProducto(productoRequest);
    }

}

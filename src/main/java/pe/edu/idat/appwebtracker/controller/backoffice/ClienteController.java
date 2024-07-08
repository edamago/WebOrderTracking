package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.request.ClienteRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.ClienteService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/cliente")
public class ClienteController {
    private ClienteService clienteService;

    @GetMapping("")
    public String frmMantCliente(Model model){
        model.addAttribute("listarclientes",clienteService.listarClientes());
        return "backoffice/cliente/frmcliente";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Cliente>listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarCliente(@RequestBody ClienteRequest clienteRequest){
        return clienteService.guardarCliente(clienteRequest);
    }
}

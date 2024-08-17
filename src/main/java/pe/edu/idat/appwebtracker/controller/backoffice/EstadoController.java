package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.bd.Estado;
import pe.edu.idat.appwebtracker.service.EstadoService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/estado")
public class EstadoController {
    private EstadoService estadoService;
    @GetMapping("/listar")
    @ResponseBody
    public List<Estado> listarEstados(){return estadoService.listarEstados();}

}

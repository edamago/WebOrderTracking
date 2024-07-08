package pe.edu.idat.appwebtracker.controller.backoffice;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.request.UsuarioRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.UsuarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/usuario")
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping("")
    public String frmMantUsuario(Model model){
        model.addAttribute("listausuarios",usuarioService.listarUsuarios());
        return "backoffice/usuario/frmusuario";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Usuario>listarUsuarios(){
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest){

        return usuarioService.guardarUsuario(usuarioRequest);
    }
}

package pe.edu.idat.appwebtracker.controller.frontoffice;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.security.UsuarioSecurity;
import pe.edu.idat.appwebtracker.service.UsuarioService;

import java.security.PublicKey;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class LoginSecurityController {

    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(){
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/registrar")
    public String registrar(){
        return "frontoffice/auth/frmRegistroUsuario";
    }

    @PostMapping("/guardarusuario")
    public String guardarusuario(@ModelAttribute Usuario usuario){
        usuarioService.guardarUsuarioSecurity(usuario);
        return "frontoffice/auth/frmLogin";
    }

    @GetMapping("/login-success")
    public String loginSuccess(){
        return "redirect:/auth/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpServletRequest request){
        HttpSession session=request.getSession();
        UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        UsuarioSecurity usuarioSecurity=(UsuarioSecurity) userDetails;
        session.setAttribute("usuario",usuarioSecurity.getUsername());
        return "frontoffice/auth/home";
    }

}
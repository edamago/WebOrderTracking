package pe.edu.idat.appwebtracker.controller.backoffice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pe.edu.idat.appwebtracker.model.bd.LoginModel;


@Controller
/*@RequestMapping("/backoffice/cliente")*/
public class LoginController {
    @GetMapping("/autenticacion")
    public String login(Model model){
        model.addAttribute("loginmodel",new LoginModel());
        model.addAttribute("visualizar", false);
        return "frmlogin";
    }
    @PostMapping("/login")
    public String login(@ModelAttribute("loginmodel") LoginModel loginModel,
                        Model model){
        if (loginModel.getUsuario().equals("puentes")
                && loginModel.getPassword().equals("1234")){
            model.addAttribute("mensaje",
                    "bienvenido: " + loginModel.getUsuario());
            return "home";
        } else if (loginModel.getUsuario().equals("martel")
                && loginModel.getPassword().equals("1234")) {
            model.addAttribute("mensaje",
                    "bienvenido: " + loginModel.getUsuario());
            return "home";

        }
        model.addAttribute("visualizar",true);
        return "frmlogin";
    }
}

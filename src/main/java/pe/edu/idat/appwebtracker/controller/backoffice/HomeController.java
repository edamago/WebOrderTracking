package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@NoArgsConstructor
@Controller
public class HomeController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
}

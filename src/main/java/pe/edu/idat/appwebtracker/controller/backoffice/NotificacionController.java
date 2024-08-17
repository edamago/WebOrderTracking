package pe.edu.idat.appwebtracker.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.idat.appwebtracker.model.bd.Notificacion;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.request.EstadoDetallePedidoRequest;
import pe.edu.idat.appwebtracker.model.request.NotificacionRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.service.NotificacionService;
import pe.edu.idat.appwebtracker.service.UsuarioService;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/notificacion")
public class NotificacionController {
    private NotificacionService notificacionService;

    @GetMapping("")
    public String frmMantNotificacion(Model model){
        model.addAttribute("listanotificaciones",notificacionService.listarNotificaciones());
        return "backoffice/notificacion/frmnotificacion";
    }
    @GetMapping("/listar")
    @ResponseBody
    public List<Notificacion> listarNotificaciones(){
        return notificacionService.listarNotificaciones();
    }

    @PostMapping("/guardar")
    @ResponseBody
    public ResultadoResponse guardarNotificacion(@RequestBody NotificacionRequest notificacionRequest){
        return notificacionService.guardarNotificacion(notificacionRequest);
    }
}

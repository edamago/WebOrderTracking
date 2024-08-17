package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.bd.Notificacion;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.request.ClienteRequest;
import pe.edu.idat.appwebtracker.model.request.NotificacionRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.NotificacionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificacionService {
    private NotificacionRepository notificacionRepository;

    public List<Notificacion>listarNotificaciones(){return notificacionRepository.findAll();}

    public ResultadoResponse guardarNotificacion(NotificacionRequest notificacionRequest){
        String mensaje="Notificacion registrado correctamente";
        Boolean respuesta=true;
        try{
            Notificacion notificacion = new Notificacion();
            if(notificacionRequest.getId()>0){
                notificacion.setId(notificacionRequest.getId());
            }
            notificacion.setDescripcion(notificacionRequest.getDescripcion());
            notificacion.setEstado(notificacionRequest.getEstado());
            notificacion.setVisto(notificacionRequest.getVisto());

            Usuario usuario = new Usuario();
            usuario.setId(notificacionRequest.getT_usuario_id());
            notificacion.setUsuario(usuario);


            notificacionRepository.save(notificacion);
        }catch (Exception ex){
            mensaje="Notificación no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}

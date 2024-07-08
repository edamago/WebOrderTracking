package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Rol;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.request.UsuarioRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.RolRepository;
import pe.edu.idat.appwebtracker.repository.UsuarioRepository;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
@AllArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private RolRepository rolRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder();

    public Usuario obtenerUsuarioPorCorreo(String correo){
        return usuarioRepository.findBycorreo(correo);
    }

    public  Usuario obtenerUsuarioPorNombre(String nombre){
        return usuarioRepository.findBynomusuario(nombre);
    }

    public List<Usuario>listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public ResultadoResponse guardarUsuario(UsuarioRequest usuarioRequest){
        String mensaje="Usuario registrado correctamente";
        Boolean respuesta=true;
        try{
            Usuario usuario = new Usuario();
            if(usuarioRequest.getId()>0){
                usuario.setId(usuarioRequest.getId());
            }
            usuario.setNombre(usuarioRequest.getNombre());
            usuario.setCorreo(usuarioRequest.getCorreo());
            usuario.setEstado(usuarioRequest.getEstado());
            usuario.setActivo(usuarioRequest.getActivo());
            usuario.setNomusuario(usuarioRequest.getNomusuario());
            /*usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));*/
            usuario.setPassword(bCryptPasswordEncoder.encode(usuarioRequest.getPassword()));

            usuarioRepository.save(usuario);
        }catch (Exception ex){
            mensaje="usuario no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

    public Usuario guardarUsuarioSecurity(Usuario usuario){
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        usuario.setEstado("A");

        Rol usuarioRol=rolRepository.findBynomrol("ADMIN");
        usuario.setRoles(new HashSet<>(Arrays.asList(usuarioRol)));

        return usuarioRepository.save(usuario);
    }

}

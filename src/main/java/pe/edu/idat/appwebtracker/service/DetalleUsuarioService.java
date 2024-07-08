package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Rol;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.security.UsuarioSecurity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DetalleUsuarioService implements UserDetailsService {
    private UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=usuarioService.obtenerUsuarioPorNombre(username);
        return autenticacionUsuario(usuario,obtenerListadoRolesPorUsuario(usuario.getRoles()));
    }

    private List<GrantedAuthority>obtenerListadoRolesPorUsuario(Set<Rol> listRoles){
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for(Rol rol:listRoles){
            roles.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        List<GrantedAuthority>grantedAuthorities=new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UsuarioSecurity autenticacionUsuario(
            Usuario usuario, List<GrantedAuthority> authorityList
    ){
        UsuarioSecurity usuarioSecurity=new UsuarioSecurity(usuario.getNombre(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,authorityList);

        usuarioSecurity.setEmail(usuario.getCorreo());
        usuarioSecurity.setNombres(usuario.getNombre());
        return  usuarioSecurity;
    }

}

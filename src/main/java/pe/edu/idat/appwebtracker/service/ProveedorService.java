package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Proveedor;
import pe.edu.idat.appwebtracker.model.request.ProveedorRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.ProveedorRepository;

import java.net.http.HttpResponse;
import java.util.List;

@AllArgsConstructor
@Service
public class ProveedorService {
    private ProveedorRepository proveedorRepository;

    public List<Proveedor>listarProveedores(){
        return proveedorRepository.findAll();
    }

    public ResultadoResponse guardarProveedor(ProveedorRequest proveedorRequest){
        String mensaje="Proveedor guardado correctamente";
        Boolean respuesta=true;

        try{
            Proveedor proveedor = new Proveedor();
            if(proveedorRequest.getId()>0){
                proveedor.setId(proveedorRequest.getId());
            }
            proveedor.setTipo_documento(proveedorRequest.getTipo_documento());
            proveedor.setDocumento(proveedorRequest.getDocumento());
            proveedor.setNombre(proveedorRequest.getNombre());
            proveedor.setDireccion(proveedorRequest.getDireccion());
            proveedor.setDistrito(proveedorRequest.getDistrito());
            proveedor.setCiudad(proveedorRequest.getCiudad());
            proveedor.setTipo(proveedorRequest.getTipo());
            proveedor.setComentarios(proveedorRequest.getComentarios());
            proveedor.setEstado(proveedorRequest.getEstado());
            proveedorRepository.save(proveedor);
        }
        catch (Exception ex){
            mensaje="Proveedor no registrado";
            respuesta=false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}

package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Cliente;
import pe.edu.idat.appwebtracker.model.bd.Producto;
import pe.edu.idat.appwebtracker.model.request.ClienteRequest;
import pe.edu.idat.appwebtracker.model.request.ProductoRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.ClienteRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){return clienteRepository.findAll();}

    public ResultadoResponse guardarCliente(ClienteRequest clienteRequest){
        String mensaje="Cliente registrado correctamente";
        Boolean respuesta=true;
        try{
            Cliente cliente = new Cliente();
            if(clienteRequest.getId()>0){
                cliente.setId(clienteRequest.getId());
            }
            cliente.setTipo_documento(clienteRequest.getTipo_documento());
            cliente.setDocumento(clienteRequest.getDocumento());
            cliente.setNombre(clienteRequest.getNombre());
            cliente.setDireccion(clienteRequest.getDireccion());
            cliente.setDireccion_entrega(clienteRequest.getDireccion_entrega());
            cliente.setDistrito(clienteRequest.getDistrito());
            cliente.setCiudad(clienteRequest.getCiudad());
            cliente.setTipo(clienteRequest.getTipo());
            cliente.setClasif_comercial(clienteRequest.getClasif_comercial());
            cliente.setComentarios(clienteRequest.getComentarios());
            cliente.setEstado(clienteRequest.getEstado());

            clienteRepository.save(cliente);
        }catch (Exception ex){
            mensaje="Cliente no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }

}

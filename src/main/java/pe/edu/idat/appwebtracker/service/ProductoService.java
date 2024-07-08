package pe.edu.idat.appwebtracker.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.idat.appwebtracker.model.bd.Producto;
import pe.edu.idat.appwebtracker.model.bd.Usuario;
import pe.edu.idat.appwebtracker.model.request.ProductoRequest;
import pe.edu.idat.appwebtracker.model.request.UsuarioRequest;
import pe.edu.idat.appwebtracker.model.response.ResultadoResponse;
import pe.edu.idat.appwebtracker.repository.ProductoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductoService {
    private ProductoRepository productoRepository;

    public List<Producto>listarProductos(){
        return productoRepository.findAll();
    }

    public ResultadoResponse guardarProducto(ProductoRequest productoRequest){
        String mensaje="Producto registrado correctamente";
        Boolean respuesta=true;
        try{
            Producto producto = new Producto();
            if(productoRequest.getId()>0){
                producto.setId(productoRequest.getId());
            }
            producto.setDescripcion(productoRequest.getDescripcion());
            producto.setUnidad_medida(productoRequest.getUnidad_medida());
            producto.setStock_minimo(productoRequest.getStock_minimo());
            producto.setStock_maximo(productoRequest.getStock_maximo());
            producto.setPeso_bruto(productoRequest.getPeso_bruto());
            producto.setPeso_neto(productoRequest.getPeso_neto());
            producto.setAlto(productoRequest.getAlto());
            producto.setAncho(productoRequest.getAncho());
            producto.setProfundo(productoRequest.getProfundo());
            producto.setClasif_demanda(productoRequest.getClasif_demanda());
            producto.setClasif_comercial(productoRequest.getClasif_comercial());
            producto.setComentarios(productoRequest.getComentarios());
            producto.setEstado(productoRequest.getEstado());

            productoRepository.save(producto);
        }catch (Exception ex){
            mensaje="Producto no registrado";
            respuesta = false;
        }
        return ResultadoResponse.builder().mensaje(mensaje).respuesta(respuesta).build();
    }
}

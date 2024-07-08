package pe.edu.idat.appwebtracker.model.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ProductoRequest {
    private Integer id;
    private String descripcion;
    private String unidad_medida;
    private Integer stock_minimo;
    private Integer stock_maximo;
    private Double peso_bruto;
    private Double peso_neto;
    private Double alto;
    private Double ancho;
    private Double profundo;
    private String clasif_demanda;
    private String clasif_comercial;
    private String comentarios;
    private String estado;
}

package pe.edu.idat.appwebtracker.model.bd;


import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "unidad_medida")
    private String unidad_medida;
    @Column(name = "stock_minimo")
    private Integer stock_minimo;
    @Column(name = "stock_maximo")
    private Integer stock_maximo;
    @Column(name = "peso_bruto")
    private Double peso_bruto;
    @Column(name = "peso_neto")
    private Double peso_neto;
    @Column(name = "alto")
    private Double alto;
    @Column(name = "ancho")
    private Double ancho;
    @Column(name = "profundo")
    private Double profundo;
    @Column(name = "clasif_demanda")
    private String clasif_demanda;
    @Column(name = "clasif_comercial")
    private String clasif_comercial;
    @Column(name = "comentarios")
    private String comentarios;
    @Column(name = "estado")
    private String estado;
}

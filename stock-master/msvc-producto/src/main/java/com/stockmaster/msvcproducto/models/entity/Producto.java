package com.stockmaster.msvcproducto.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "productos")
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nombre;
    private String etiqueta;
    @NotNull
    private Double precio;
    private Integer stockActual;
    private Integer stockMinino;
    private Double precioPromocion;
    private String descripcion;
    private Double costo;
    private String venederPor;
    @ManyToOne
    private Categoria categoria;


}

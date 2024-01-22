package com.stockmaster.msvcproducto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
    private Double precio;
    private Integer stockActual;
    private Integer stockMinino;
    private Double precioPromocion;
    private Double costo;
    @ManyToOne
    private Categoria categotia;


}

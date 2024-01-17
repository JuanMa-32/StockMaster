package com.stockmaster.msvcproducto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "productos")
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

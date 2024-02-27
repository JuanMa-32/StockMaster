package com.negocio.msvcnegocio.models;

import lombok.Data;

@Data
public class Producto {

    private Long id;
    private String nombre;
    private String etiqueta;
    private Double precio;
    private Integer stockActual;
    private Integer stockMinino;
    private Double precioPromocion;
    private String descripcion;
    private Double costo;
    private String venederPor;

}

package com.stock.master.msvcventa.models;

import lombok.Data;

@Data
public class Usuario {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private Integer ventas;
    private Double facturacion;
}

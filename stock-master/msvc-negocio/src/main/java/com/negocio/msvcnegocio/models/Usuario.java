package com.negocio.msvcnegocio.models;

import lombok.Data;

@Data
public class Usuario {
    private Long id;
    private Long idNegocio;
    private String nombre;
    private String email;
    private String password;
    private Integer ventas;
    private Double facturacion;
}

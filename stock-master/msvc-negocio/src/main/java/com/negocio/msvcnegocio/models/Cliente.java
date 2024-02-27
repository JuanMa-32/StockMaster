package com.negocio.msvcnegocio.models;

import lombok.Data;

@Data
public class Cliente {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String observaciones;
    private String telefono;
    private String direccion;
    private String complemento;
}

package com.stockmaster.msvccliente.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotBlank
    private String nombre;
    private String apellido;
    @NotBlank
    @Email
    private String email;

    private String password;
    private String observaciones;
    private String telefono;
    private String direccion;
    private String complemento;

}

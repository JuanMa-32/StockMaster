package com.stock.master.msvcusuario.entity;

import com.stock.master.msvcusuario.roles.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Completar Nombre")
    private String nombre;
    private Long idNegocio;

    @Email
    @Column(unique = true)
    private String email;
    @NotNull
    private String password;
    private Integer ventas;
    private Double facturacion;

    @Enumerated(EnumType.STRING)
    private Rol rol;



}

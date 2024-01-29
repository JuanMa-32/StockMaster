package com.negocio.msvcnegocio.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "negocios")
@Data
public class Negocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Nombre;
    private Long idUsuario;
    private List<Long>idProductos;
    public void addProducto(Long idProducto){
        this.idProductos.add(idProducto);
    }

}

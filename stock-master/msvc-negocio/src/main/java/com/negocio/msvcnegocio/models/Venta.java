package com.negocio.msvcnegocio.models;


import lombok.Data;

import java.util.Date;

@Data
public class Venta {

    private String id;


    private Date fecha;
    private Double subTotal;
    private Double total;
    private Double descuento;
    private String metodoPago;
    private String observacion;
    private Long idUsuario;
    private Long idCliente;
    private Usuario usuario;

    private Cliente cliente;
    private Integer items;
}

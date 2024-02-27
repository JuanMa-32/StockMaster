package com.stockmaster.msvcproducto.entity;


import lombok.Data;




@Data
public class ProductoVenta {

    private String id;
    private Long idProducto;
    private Double precioTotalItem;
    private String nombreProducto;
    private Integer itemsProducto;



}

package com.stock.master.msvcventa.models.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productosVenta")
@Data
public class ProductoVenta {
    @Id
    private String id;
    private Long idProducto;
    private Double precioTotalItem;
    private String nombreProducto;
    private Integer itemsProducto;



}

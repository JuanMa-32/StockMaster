package com.stock.master.msvcventa.models.entity;

import com.stock.master.msvcventa.models.Cliente;
import com.stock.master.msvcventa.models.Usuario;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;
import java.util.List;

@Document(collection = "ventas")//marcar como una entity que se almacenara en mongo
@Data
public class Venta {

    @Id
    private String id;

    @Field(targetType = FieldType.DATE_TIME)
    private Date fecha;
    private Double subTotal;
    private Double total;
    private Double descuento;
    private String metodoPago;
    private String observacion;

    private Long idUsuario;

    private Long idCliente;

    @Transient //esto indica que no sera persisitido en la db
    private Usuario usuario;
    @Transient
    Cliente cliente;
    private Integer items;

    private List<ProductoVenta> productos;



}

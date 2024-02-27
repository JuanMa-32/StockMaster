package com.stock.master.msvcventa.clients;


import com.stock.master.msvcventa.models.entity.ProductoVenta;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(name = "msvc-producto" ,url="${msvc.producto.url}")
public interface ProductoClientRest {
    @PutMapping("/restarStock")
    void restarStock (@RequestBody List<ProductoVenta> listaProductoVenta);

}

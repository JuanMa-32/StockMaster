package com.stock.master.msvcventa.clients;


import com.stock.master.msvcventa.models.entity.ProductoVenta;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "msvc-producto",url = "localhost:8003")
public interface ProductoClientRest {
    @PutMapping("/restarStock")
    public void restarStock (@RequestBody List<ProductoVenta> listaProductoVenta);
}

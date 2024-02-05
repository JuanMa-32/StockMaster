package com.negocio.msvcnegocio.clients;

import com.negocio.msvcnegocio.entity.Producto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-producto",url = "localhost:8004")
public interface ClienteClientRest {
    @GetMapping("/verificarProducto/{id}")
    Producto verificarProducto(@PathVariable Long id);
}

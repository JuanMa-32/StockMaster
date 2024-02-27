package com.negocio.msvcnegocio.clients;

import com.negocio.msvcnegocio.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-producto",url = "${msvc.producto.url}")
public interface ProductoClientRest {
    @GetMapping
    List<Producto> findAll(@RequestParam List<Long> ids);
}

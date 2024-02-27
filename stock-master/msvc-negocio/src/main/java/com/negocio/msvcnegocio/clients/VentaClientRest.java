package com.negocio.msvcnegocio.clients;

import com.negocio.msvcnegocio.models.Venta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "msvc-venta",url = "${msvc.venta.url}")
public interface VentaClientRest {
    @GetMapping
    List<Venta>findAll(@RequestParam List<String> ids);
}

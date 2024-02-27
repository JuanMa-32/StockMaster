package com.stock.master.msvcventa.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-negocio", url = "${msvc.negocio.url}")
public interface NegocioClientRest {
    @PutMapping("/agregar-venta")
    void agregarVenta(@RequestParam String id, @RequestParam Long idNegocio);
}

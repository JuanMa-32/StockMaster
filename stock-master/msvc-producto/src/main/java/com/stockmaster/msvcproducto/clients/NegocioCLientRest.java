package com.stockmaster.msvcproducto.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-negocio", url = "${msvc.negocio.url}")
public interface NegocioCLientRest {
    @PutMapping("/agregar-producto")
    void agregarProducto(@RequestParam Long id, @RequestParam Long idNegocio);
    @DeleteMapping("/eliminar-producto")
    ResponseEntity<?> deleteProducto(@RequestParam Long id,@RequestParam Long idNegocio);
}

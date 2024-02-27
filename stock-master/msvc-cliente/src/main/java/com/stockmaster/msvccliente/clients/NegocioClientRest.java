package com.stockmaster.msvccliente.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-negocio", url = "${msvc.negocio.url}")
public interface NegocioClientRest {
    @PutMapping("/agregar-client")
    void agregarCliente(@RequestParam Long id, @RequestParam Long idNegocio);
    @DeleteMapping("/eliminar-cliente")
    ResponseEntity<?> deleteCliente(@RequestParam Long id,@RequestParam Long idNegocio);
}

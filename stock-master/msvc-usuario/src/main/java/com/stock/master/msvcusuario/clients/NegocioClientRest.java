package com.stock.master.msvcusuario.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-negocio", url = "${msvc.negocio.url}")
public interface NegocioClientRest {
    @PutMapping("/agregar-usuario")
    void agregarUs(@RequestParam Long id,@RequestParam Long idNegocio);
}

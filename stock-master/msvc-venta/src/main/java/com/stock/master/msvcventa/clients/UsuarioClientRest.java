package com.stock.master.msvcventa.clients;

import com.stock.master.msvcventa.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "msvc-usuario", url = "localhost:8001")
public interface UsuarioClientRest {
    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);

    @PutMapping("/agregar-facturacion/{id}")
    Usuario nuevaVenta(@PathVariable Long id,@RequestParam Double facturacion);
}

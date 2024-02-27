package com.negocio.msvcnegocio.clients;


import com.negocio.msvcnegocio.models.Usuario;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "msvc-usuario", url = "${msvc.usuario.url}")
public interface UsuarioClientRest {
    @GetMapping
    List<Usuario> findAll(@RequestParam List<Long> ids);
    @PostMapping("/{idNegocio}")
    Usuario guardarUsuario(@RequestBody Usuario usuario,@PathVariable Long idNegocio);

}

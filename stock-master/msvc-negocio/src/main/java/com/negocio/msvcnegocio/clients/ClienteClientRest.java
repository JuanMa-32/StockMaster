package com.negocio.msvcnegocio.clients;



import com.negocio.msvcnegocio.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "msvc-cliente",url = "${msvc.cliente.url}")
public interface ClienteClientRest {
    @GetMapping
    List<Cliente> findAll(@RequestParam List<Long> ids);
}

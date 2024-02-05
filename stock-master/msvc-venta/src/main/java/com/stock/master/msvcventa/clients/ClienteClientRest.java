package com.stock.master.msvcventa.clients;

import com.stock.master.msvcventa.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cliente",url = "localhost:8002")
public interface ClienteClientRest {
    @GetMapping("/{id}")
    Cliente findById(@PathVariable Long id);
}

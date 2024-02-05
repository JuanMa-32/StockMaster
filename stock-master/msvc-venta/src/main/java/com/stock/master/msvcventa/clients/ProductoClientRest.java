package com.stock.master.msvcventa.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "msvc-producto" ,url="localhost:8003")
public interface ProductoClientRest {

}

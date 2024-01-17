package com.stockmaster.msvcproducto.controller;

import com.stockmaster.msvcproducto.service.ProductoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")
@RestController
public class ProductoController {
    private ProductoService productoService;

}

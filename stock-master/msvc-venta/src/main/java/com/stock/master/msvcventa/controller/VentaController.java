package com.stock.master.msvcventa.controller;

import com.stock.master.msvcventa.models.entity.Venta;
import com.stock.master.msvcventa.services.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaServiceImpl service;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam List<String> ids){
        return ResponseEntity.ok(service.findAllUsuarioAndCliente(ids));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Venta> o = service.findByIdUsuarioAndCliente(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{idNegocio}")
    public ResponseEntity<?> save(@RequestBody Venta venta,@PathVariable Long idNegocio){
        venta.setFecha(new Date());
        return ResponseEntity.status(201).body(service.save(venta,idNegocio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }

}

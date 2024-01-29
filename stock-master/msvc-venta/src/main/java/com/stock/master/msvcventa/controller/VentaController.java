package com.stock.master.msvcventa.controller;

import com.stock.master.msvcventa.models.entity.Venta;
import com.stock.master.msvcventa.services.VentaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class VentaController {

    @Autowired
    private VentaServiceImpl service;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAllUsuarioAndCliente());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Venta> o = service.findByIdUsuarioAndCliente(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Venta venta){
        venta.setFecha(new Date());
        return ResponseEntity.status(201).body(service.save(venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        service.eliminarVenta(id);
        return ResponseEntity.noContent().build();
    }
}

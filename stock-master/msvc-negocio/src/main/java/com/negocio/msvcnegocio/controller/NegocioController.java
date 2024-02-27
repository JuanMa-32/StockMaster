package com.negocio.msvcnegocio.controller;


import com.negocio.msvcnegocio.models.entity.Negocio;
import com.negocio.msvcnegocio.service.NegocioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class NegocioController {
    @Autowired
    private NegocioService negocioService;
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Negocio> o=negocioService.findById(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> usuariosNegocio(@PathVariable Long id) {
        Optional<Negocio> o = negocioService.findById(id);
        if (o.isPresent()) {
            Negocio negocioDb = o.get();
            return ResponseEntity.ok(negocioDb.getUsuarios());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<?> clientesNegocio(@PathVariable Long id) {
        Optional<Negocio> o = negocioService.findById(id);
        if (o.isPresent()) {
            Negocio negocioDb = o.get();
            return ResponseEntity.ok(negocioDb.getClientes());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/productos/{id}")
    public ResponseEntity<?> ProductosNegocio(@PathVariable Long id) {
        Optional<Negocio> o = negocioService.findById(id);
        if (o.isPresent()) {
            Negocio negocioDb = o.get();
            return ResponseEntity.ok(negocioDb.getProductos());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/ventas/{id}")
    public ResponseEntity<?> ventasNegocio(@PathVariable Long id) {
        Optional<Negocio> o = negocioService.findById(id);
        if (o.isPresent()) {
            Negocio negocioDb = o.get();
            return ResponseEntity.ok(negocioDb.getVentas());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Negocio negocio, BindingResult result) {

        if ((result.hasErrors())) {
            return validar(result);
        }

        Negocio negocioDb = negocioService.save(negocio);
        negocio.getUsuarios().get(0).setIdNegocio(negocioDb.getId());
        negocioService.guardarDueno(negocio.getUsuarios().get(0),negocioDb.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(negocioDb);
    }

    @PutMapping("/configuraciones/{id}")
    public ResponseEntity<?> updateNegocio(@RequestBody Negocio negocio,@PathVariable Long id){
        Optional<Negocio> o = negocioService.findById(id);
        if(o.isPresent()){
            Negocio negocioDb = o.get();
            negocioDb.setNombreComercio(negocio.getNombreComercio());
            negocioDb.setNombreResponsable(negocio.getNombreResponsable());
            negocioDb.setCedulaIdentidad(negocio.getCedulaIdentidad());
            negocioDb.setCelular(negocio.getCelular());
            negocioDb.setEmail(negocio.getEmail());
            negocioDb.setDireccion(negocio.getDireccion());
            negocioDb.setComplemento(negocio.getComplemento());
            negocioDb.setInformacion(negocio.getInformacion());
            return ResponseEntity.status(201).body(negocioService.save(negocioDb));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/agregar-usuario")
    public ResponseEntity<?> agregarUs(@RequestParam Long id,@RequestParam Long idNegocio) {

        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.agregarUsuario(id);
            negocioService.save(negocio);
        }
        return ResponseEntity.status(204).build();
    }
    @PutMapping("/agregar-venta")
    public ResponseEntity<?> agregarVenta(@RequestParam String id,@RequestParam Long idNegocio) {

        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.agregarVenta(id);
            negocioService.save(negocio);
        }
        return ResponseEntity.status(204).build();
    }
     @PutMapping("/agregar-client")
     public ResponseEntity<?> agregarCliente(@RequestParam Long id,@RequestParam Long idNegocio){
        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.agregarCliente(id);
            negocioService.save(negocio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
     }
    @PutMapping("/agregar-producto")
    public ResponseEntity<?> agregarProducto(@RequestParam Long id,@RequestParam Long idNegocio){
        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.addProducto(id);
            negocioService.save(negocio);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-cliente")
    public ResponseEntity<?> deleteCliente(@RequestParam Long id,@RequestParam Long idNegocio){
        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.eliminarIdCliente(id);
            return ResponseEntity.ok( negocioService.save(negocio));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/eliminar-producto")
    public ResponseEntity<?> deleteProducto(@RequestParam Long id,@RequestParam Long idNegocio){
        Optional<Negocio> o = negocioService.findById(idNegocio);
        if(o.isPresent()){
            Negocio negocio = o.get();
            negocio.eliminarIdProducto(id);
            return ResponseEntity.ok( negocioService.save(negocio));
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "campo :" + "" + e.getField() + "" + e.getDefaultMessage());
        });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }
}

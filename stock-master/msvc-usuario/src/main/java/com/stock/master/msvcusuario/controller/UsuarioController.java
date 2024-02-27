package com.stock.master.msvcusuario.controller;

import com.stock.master.msvcusuario.entity.Usuario;
import com.stock.master.msvcusuario.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(service.findAllById(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            Usuario usuarioDb = o.get();
            return ResponseEntity.ok(usuarioDb);
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email){
        Optional<Usuario> o = service.findByEmail(email);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{idNegocio}")
    public ResponseEntity<?> guardarUsuario(@Valid @RequestBody Usuario usuario, BindingResult result,@PathVariable Long idNegocio) {

        if (result.hasErrors()) {
            return validation(result);
        }
        usuario.setIdNegocio(idNegocio);
        return ResponseEntity.status(201).body(service.save(usuario,idNegocio));
    }

    @PutMapping("/agregar-facturacion/{id}")
    public ResponseEntity<?> nuevaVenta(@PathVariable Long id, @RequestParam Double facturacion) {
        Optional<Usuario> o = service.findById(id);
        if (o.isPresent()) {
            Usuario us = o.get();
            if (us.getVentas() == null) {
                us.setVentas(1);
                us.setFacturacion(facturacion);
            } else {
                us.setVentas(us.getVentas() + 1);
                us.setFacturacion(us.getFacturacion() + facturacion);
            }
            service.actualizarUsuario(us);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PutMapping("/cambiar-password/{id}")
    public ResponseEntity cambiarContrasenia(@PathVariable Long id, @RequestParam String password){
        Optional<Usuario> o = service.findById(id);
        if(o.isPresent()) {
            Usuario usuario = o.get();
            usuario.setPassword(password);
            service.actualizarUsuario(usuario);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


    private ResponseEntity<?> validation(BindingResult result) {
        HashMap<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}

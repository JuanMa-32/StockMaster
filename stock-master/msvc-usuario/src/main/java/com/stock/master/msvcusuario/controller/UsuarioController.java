package com.stock.master.msvcusuario.controller;

import com.stock.master.msvcusuario.entity.Usuario;
import com.stock.master.msvcusuario.service.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImpl service;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id){
        Optional<Usuario> o = service.findById(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();
            return ResponseEntity.ok(usuarioDb);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping
    public ResponseEntity guardarUsuario(@Valid @RequestBody Usuario usuario,BindingResult result){
        if(result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.status(201).body(service.save(usuario));
    }



    private ResponseEntity<?> validation(BindingResult result){
        HashMap<String,String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}

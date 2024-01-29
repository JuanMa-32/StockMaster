package com.negocio.msvcnegocio.controller;

import com.negocio.msvcnegocio.entity.Negocio;
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
    private ResponseEntity<?>validar(BindingResult result){
        Map<String,Object>errores = new HashMap<>();
       result.getFieldErrors().forEach(e->{
           errores.put(e.getField(),"el campo" + "" + e.getField() + " "+ e.getDefaultMessage());
       });
        return new ResponseEntity<>(errores, HttpStatus.NOT_FOUND);
    }
    @GetMapping
    public ResponseEntity<?>findAll(){
      return ResponseEntity.ok().body( negocioService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Negocio>negocioOptional = negocioService.findById(id);
        if (negocioOptional.isPresent()){
            return ResponseEntity.ok().body(negocioOptional.get());
        }
        return  ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save(@Valid @RequestBody Negocio negocio,BindingResult result){
        if ((result.hasErrors())){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(negocioService.save(negocio));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){
        Optional<Negocio>negocioOptional = negocioService.findById(id);
        if(negocioOptional.isPresent()){
            return ResponseEntity.noContent().build();
        }
            return ResponseEntity.notFound().build();
    }


}

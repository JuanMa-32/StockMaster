package com.stockmaster.msvccliente.controller;

import com.stockmaster.msvccliente.entity.Cliente;
import com.stockmaster.msvccliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("")
public class ClienteControler {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok().body(clienteService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Cliente>clienteOptional = clienteService.findById(id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok().body(clienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save (@RequestBody Cliente cliente){
        return ResponseEntity.ok().body(clienteService.save(cliente));

    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update(@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente>clienteOptional = clienteService.findById(id);
        Cliente clienteDb = null;
        if (clienteOptional.isPresent()){
            clienteDb = clienteOptional.get();
            clienteDb.setNombre(cliente.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDb));

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteById(@PathVariable Long id){

        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
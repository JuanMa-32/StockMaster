package com.stockmaster.msvccliente.controller;

import com.stockmaster.msvccliente.entity.Cliente;
import com.stockmaster.msvccliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("")
public class ClienteControler {
    @Autowired
    private ClienteService clienteService;
    private ResponseEntity<?>validar(BindingResult result){
        Map<String,Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
            errores.put(e.getField(), "El campo:  "+ e.getField()+ " "+ e.getDefaultMessage() );
        });
        return new ResponseEntity<>(errores,HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam List<Long> ids){
        return ResponseEntity.ok().body(clienteService.findAll(ids));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Cliente>clienteOptional = clienteService.findById(id);
        if (clienteOptional.isPresent()){
            return ResponseEntity.ok().body(clienteOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/{idNegocio}")
    public ResponseEntity<?>save (@Valid @RequestBody Cliente cliente,BindingResult result,@PathVariable Long idNegocio){
        if (result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.ok().body(clienteService.save(cliente,idNegocio));

    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update(@Valid @RequestBody Cliente cliente,BindingResult result, @PathVariable Long id){

        if (result.hasErrors()){
            return validar(result);
        }
        Optional<Cliente>clienteOptional = clienteService.findById(id);
        Cliente clienteDb = null;
        if (clienteOptional.isPresent()){
            clienteDb = clienteOptional.get();
            clienteDb.setNombre(cliente.getNombre());
            clienteDb.setApellido(clienteDb.getApellido());
            clienteDb.setEmail(clienteDb.getEmail());
            clienteDb.setDireccion(clienteDb.getDireccion());
            clienteDb.setObservaciones(clienteDb.getObservaciones());
            clienteDb.setTelefono(clienteDb.getTelefono());
            clienteDb.setComplemento(clienteDb.getComplemento());
            clienteDb.setPassword(clienteDb.getPassword());
            clienteService.actualizarCliente(clienteDb);
            return ResponseEntity.status(204).build();

        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}/{idNegocio}")
    public ResponseEntity<?>deleteById(@PathVariable Long id,@PathVariable Long idNegocio){

        clienteService.deleteById(id,idNegocio);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/verificarCliente/{id}")
    public ResponseEntity<?>verificarCliente(@PathVariable Long id){
        Boolean verificar;
        Optional<Cliente>clienteOptional = clienteService.findById(id);
        if (clienteOptional.isPresent()){
            verificar=true;
            return ResponseEntity.ok().body(verificar);
        }
        verificar = false;
        return ResponseEntity.ok().body(verificar);
    }



}

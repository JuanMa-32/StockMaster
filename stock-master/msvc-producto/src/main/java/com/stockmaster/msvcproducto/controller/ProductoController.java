package com.stockmaster.msvcproducto.controller;

import com.stockmaster.msvcproducto.entity.Categoria;
import com.stockmaster.msvcproducto.entity.Producto;
import com.stockmaster.msvcproducto.service.CategoriaService;
import com.stockmaster.msvcproducto.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class ProductoController {
    private ProductoService productoService;
    private CategoriaService categoriaService;

    /**
     *  metodo para validar
     * @param result
     * @return
     */
    private ResponseEntity<?>validar (BindingResult result){
        Map<String,Object>errores = new HashMap<>();
        result.getFieldErrors().forEach(e->{
          errores.put(e.getField(),"El campo" + e.getField() + ""+ e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * metodo para mostrar todas las categorias
     * @return
     */
    @GetMapping("/categoria")
    public ResponseEntity<?> findAllCategoria(){
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    /**
     * metodo para mostrar una categoria segun el id
     * @param id
     * @return
     */
    @GetMapping("/categoria/{id}")
    public ResponseEntity<?>findByIdCategoria(@PathVariable Long id){
        Optional<Categoria>categoriaOptional = categoriaService.findById(id);
        if (categoriaOptional.isPresent()){
            return ResponseEntity.ok().body(categoriaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * metodo para guardar categoria
     * @param categoria
     * @param result
     * @return
     */
    @PostMapping("/categoria")
    public ResponseEntity<?>saveCategoria(@Valid @RequestBody Categoria categoria,BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    /**
     * metodo para editar categoria
     * @param categoria
     * @param result
     * @param id
     * @return
     */
    @PutMapping("/categoria/{id}")
    public ResponseEntity<?>updateCategoria(@Valid @RequestBody Categoria categoria, BindingResult result,@PathVariable Long id){
        if(result.hasErrors()){
            return validar(result);
        }
        Optional<Categoria>categoriaOptional = categoriaService.findById(id);
        Categoria categoriaDb = null;
        if(categoriaOptional.isPresent()){
            categoriaDb= categoriaOptional.get();
            categoriaDb.setNombre(categoria.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaDb));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * metodo para eliminar categoria
     * @param id
     * @return
     */
    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<?>deleteCategoria(@PathVariable Long id){
        Optional<Categoria>categoriaOptional = categoriaService.findById(id);
        if (categoriaOptional.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping
    public ResponseEntity<?>findAll(){
        return ResponseEntity.ok().body(productoService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>findById(@PathVariable Long id){
        Optional<Producto>productoOptional= productoService.findById(id);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok().body(productoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<?>save(@Valid @RequestBody Producto producto, BindingResult result){
        if(result.hasErrors()){
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?>update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id){
        Optional<Producto>productoOptional = productoService.findById(id);
        Producto productoDb = null;
        if(productoOptional.isPresent()){
            productoDb = productoOptional.get();
            productoDb.setNombre(producto.getNombre());
            //aca va todo lo que vamos a modificar de producto
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(productoDb));
        }
        return  ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>delete(@PathVariable Long id){
        Optional<Producto>productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

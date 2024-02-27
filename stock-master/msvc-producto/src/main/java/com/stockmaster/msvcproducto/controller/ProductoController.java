package com.stockmaster.msvcproducto.controller;


import com.stockmaster.msvcproducto.models.ProductoVenta;
import com.stockmaster.msvcproducto.models.entity.Categoria;
import com.stockmaster.msvcproducto.models.entity.Producto;

import com.stockmaster.msvcproducto.service.CategoriaService;
import com.stockmaster.msvcproducto.service.ProductoService;
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
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    /**
     * metodo para validar
     *
     * @param result
     * @return
     */
    private ResponseEntity<?> validar(BindingResult result) {
        Map<String, Object> errores = new HashMap<>();
        result.getFieldErrors().forEach(e -> {
            errores.put(e.getField(), "El campo" + e.getField() + "" + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errores);
    }

    /**
     * metodo para mostrar todas las categorias
     *
     * @return
     */
    @GetMapping("/categoria")
    public ResponseEntity<?> findAllCategoria() {
        return ResponseEntity.ok().body(categoriaService.findAll());
    }

    /**
     * metodo para mostrar una categoria segun el id
     *
     * @param id
     * @return
     */
    @GetMapping("/categoria/{id}")
    public ResponseEntity<?> findByIdCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaService.findById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.ok().body(categoriaOptional.get());
        }
        return ResponseEntity.notFound().build();
    }


    /**
     * metodo para guardar categoria
     *
     * @param categoria
     * @param result
     * @return
     */
    @PostMapping("/categoria")
    public ResponseEntity<?> saveCategoria(@Valid @RequestBody Categoria categoria, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoria));
    }

    /**
     * metodo para editar categoria
     *
     * @param categoria
     * @param result
     * @param id
     * @return
     */
    @PutMapping("/categoria/{id}")
    public ResponseEntity<?> updateCategoria(@Valid @RequestBody Categoria categoria, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Categoria> categoriaOptional = categoriaService.findById(id);
        Categoria categoriaDb = null;
        if (categoriaOptional.isPresent()) {
            categoriaDb = categoriaOptional.get();
            categoriaDb.setNombre(categoria.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.save(categoriaDb));
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * metodo para eliminar categoria
     *
     * @param id
     * @return
     */
    @DeleteMapping("/categoria/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaService.findById(id);
        if (categoriaOptional.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam List<Long> ids) {
        return ResponseEntity.ok().body(productoService.findAllById(ids));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            return ResponseEntity.ok().body(productoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{idNegocio}")
    public ResponseEntity<?> save(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long idNegocio) {
        if (result.hasErrors()) {
            return validar(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto, idNegocio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody Producto producto, BindingResult result, @PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        Producto productoDb = null;
        if (productoOptional.isPresent()) {
            productoDb = productoOptional.get();
            productoDb.setNombre(producto.getNombre());
            productoDb.setPrecio(producto.getPrecio());
            productoDb.setPrecioPromocion(producto.getPrecioPromocion());
            productoDb.setEtiqueta(producto.getEtiqueta());
            productoDb.setVenederPor(producto.getVenederPor());
            productoDb.setCosto(producto.getCosto());
            productoDb.setDescripcion(producto.getDescripcion());
            productoDb.setStockActual(producto.getStockActual());
            productoDb.setStockMinino(producto.getStockMinino());

            productoService.actualizarProducto(productoDb);
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}/{idNegocio}")
    public ResponseEntity<?> delete(@PathVariable Long id,@PathVariable Long idNegocio) {
        Optional<Producto> productoOptional = productoService.findById(id);
        if (productoOptional.isPresent()) {
            productoService.deleteById(id,idNegocio);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/verificarProducto/{id}")
    public ResponseEntity<?> verificarProducto(@PathVariable Long id) {
        Optional<Producto> productoOptional = productoService.findById(id);
        Boolean verificar = false;
        if (productoOptional.isPresent()) {
            verificar = true;
            return ResponseEntity.ok(verificar);
        }
        return ResponseEntity.ok(verificar);
    }


    @PutMapping("/restarStock")
    public void restarStock(@RequestBody List<ProductoVenta> listaProductoVenta) {
        List<Producto> todos = productoService.findAll();
        listaProductoVenta.forEach(c -> {
            todos.forEach(t -> {
                if (c.getIdProducto() == t.getId() && t.getStockActual() != null) {
                    t.setStockActual(t.getStockActual() - c.getItemsProducto());
                    productoService.actualizarProducto(t);

                }
            });
        });
    }
}

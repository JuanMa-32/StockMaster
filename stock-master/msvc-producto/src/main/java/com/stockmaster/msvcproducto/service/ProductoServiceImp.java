package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.entity.Producto;
import com.stockmaster.msvcproducto.repository.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Override
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepositorio.save(producto);
    }

    @Override
    public void deleteById(Long id) {
    productoRepositorio.deleteById(id);
    }
}

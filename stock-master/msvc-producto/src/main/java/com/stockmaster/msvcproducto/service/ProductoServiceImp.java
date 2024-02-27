package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.clients.NegocioCLientRest;
import com.stockmaster.msvcproducto.models.entity.Producto;
import com.stockmaster.msvcproducto.repository.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImp implements ProductoService {
    @Autowired
    private ProductoRepositorio productoRepositorio;
    @Autowired
    private NegocioCLientRest negocioCLientRest;
    @Override
    public List<Producto> findAll() {
        return productoRepositorio.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepositorio.findById(id);
    }

    @Override
    public Producto save(Producto producto, Long idNegocio) {
        Producto productoDb = productoRepositorio.save(producto);
        negocioCLientRest.agregarProducto(productoDb.getId(), idNegocio);
        return productoDb;
    }

    @Override
    public void deleteById(Long id,Long idNegocio) {
   negocioCLientRest.deleteProducto(id,idNegocio);
    }

    @Override
    public List<Producto> findAllById(List<Long> ids) {

        return productoRepositorio.findAllById(ids);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        productoRepositorio.save(producto);
    }
}

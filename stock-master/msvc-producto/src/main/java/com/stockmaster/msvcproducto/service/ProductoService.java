package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.models.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> findAll();
    public Optional<Producto>findById(Long id);
    public Producto save(Producto producto,Long idNegocio);
    public void deleteById(Long id,Long idNegocio);
    public List<Producto> findAllById(List<Long> ids);
    public void actualizarProducto(Producto producto);
}

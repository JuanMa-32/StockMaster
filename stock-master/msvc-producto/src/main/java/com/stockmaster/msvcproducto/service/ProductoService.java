package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.entity.Producto;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> findAll();
    public Optional<Producto>findById(Long id);
    public Producto save(Producto producto);
    public void deleteById(Long id);
    public List<Producto> findAllById(List<Long> ids);
}

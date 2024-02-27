package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.models.entity.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    public List<Categoria>findAll();
    public Optional<Categoria>findById(Long id);
    public Categoria save(Categoria categoria);
    public void deleteById(Long id);
}

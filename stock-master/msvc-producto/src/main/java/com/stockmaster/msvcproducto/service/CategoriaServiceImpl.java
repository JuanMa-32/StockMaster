package com.stockmaster.msvcproducto.service;

import com.stockmaster.msvcproducto.models.entity.Categoria;
import com.stockmaster.msvcproducto.repository.CategoriaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoriaServiceImpl implements CategoriaService{
    @Autowired
    private CategoriaRepositorio categoriaRepositorio;
    @Override
    public List<Categoria> findAll() {
        return categoriaRepositorio.findAll();
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return categoriaRepositorio.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepositorio.save(categoria);
    }

    @Override
    public void deleteById(Long id) {
        categoriaRepositorio.deleteById(id);
    }
}

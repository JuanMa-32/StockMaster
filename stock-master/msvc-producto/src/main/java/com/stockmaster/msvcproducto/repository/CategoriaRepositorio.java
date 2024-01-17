package com.stockmaster.msvcproducto.repository;

import com.stockmaster.msvcproducto.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria,Long> {
}

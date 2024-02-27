package com.stockmaster.msvcproducto.repository;

import com.stockmaster.msvcproducto.models.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}

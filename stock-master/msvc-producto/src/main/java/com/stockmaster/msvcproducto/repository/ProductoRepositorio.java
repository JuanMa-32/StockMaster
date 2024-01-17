package com.stockmaster.msvcproducto.repository;

import com.stockmaster.msvcproducto.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}

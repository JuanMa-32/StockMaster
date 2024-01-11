package com.stock.master.msvcusuario.repository;

import com.stock.master.msvcusuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}

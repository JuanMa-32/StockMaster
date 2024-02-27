package com.stock.master.msvcusuario.service;

import com.stock.master.msvcusuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAllById(List<Long> ids);
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario,Long idNegocio);

    Optional<Usuario> findByEmail(String email);
    void actualizarUsuario(Usuario usuario);

}

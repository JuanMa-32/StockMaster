package com.stock.master.msvcusuario.service;

import com.stock.master.msvcusuario.clients.NegocioClientRest;
import com.stock.master.msvcusuario.entity.Usuario;
import com.stock.master.msvcusuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private NegocioClientRest negocioClientRest;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAllById(List<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario,Long idNegocio) {
        Usuario us = repository.save(usuario);
        negocioClientRest.agregarUs(us.getId(),idNegocio);
        return us;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void actualizarUsuario(Usuario usuario) {
        Usuario us = repository.save(usuario);
    }
}

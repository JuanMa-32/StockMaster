package com.stockmaster.msvccliente.service;

import com.stockmaster.msvccliente.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> findAll();
    public Optional<Cliente> findById(Long id);
    public Cliente save(Cliente cliente);
    public void deleteById(Long id);
}

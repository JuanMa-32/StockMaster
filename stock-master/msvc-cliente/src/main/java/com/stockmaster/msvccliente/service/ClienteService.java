package com.stockmaster.msvccliente.service;

import com.stockmaster.msvccliente.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> findAll(List<Long>ids);
    public Optional<Cliente> findById(Long id);
    public Cliente save(Cliente cliente,Long idNegocio);
    public void deleteById(Long id,Long idNegocio);
    public void actualizarCliente(Cliente cliente);
}

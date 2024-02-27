package com.stockmaster.msvccliente.service;

import com.stockmaster.msvccliente.clients.NegocioClientRest;
import com.stockmaster.msvccliente.entity.Cliente;
import com.stockmaster.msvccliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private NegocioClientRest negocioClientRest;


    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll(List<Long> ids) {
        return clienteRepository.findAllById(ids);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente, Long idNegocio) {
        Cliente clienteDb = clienteRepository.save(cliente);
        negocioClientRest.agregarCliente(cliente.getId(), idNegocio);
        return clienteDb;
    }

    @Override
    @Transactional
    public void deleteById(Long id, Long idNegocio) {
        negocioClientRest.deleteCliente(id, idNegocio);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }
}

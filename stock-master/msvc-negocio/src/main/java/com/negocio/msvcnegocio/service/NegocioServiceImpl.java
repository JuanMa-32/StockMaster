package com.negocio.msvcnegocio.service;

import com.negocio.msvcnegocio.entity.Negocio;
import com.negocio.msvcnegocio.repository.NegocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegocioServiceImpl implements NegocioService {
    @Autowired
    private NegocioRepository negocioRepository;

    @Override
    public List<Negocio> findAll() {
        return negocioRepository.findAll();
    }

    @Override
    public Optional<Negocio> findById(Long id) {
        return negocioRepository.findById(id);
    }

    @Override
    public Negocio save(Negocio negocio) {
        return negocioRepository.save(negocio);
    }

    @Override
    public void deleteById(Long id) {
        negocioRepository.deleteById(id);
    }
}

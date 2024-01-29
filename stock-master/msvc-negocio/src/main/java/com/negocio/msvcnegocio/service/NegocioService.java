package com.negocio.msvcnegocio.service;

import com.negocio.msvcnegocio.entity.Negocio;

import java.util.List;
import java.util.Optional;

public interface NegocioService {
    public List<Negocio>findAll();
    public Optional<Negocio>findById(Long id);
    public Negocio save(Negocio negocio);
    public void deleteById(Long id);
}

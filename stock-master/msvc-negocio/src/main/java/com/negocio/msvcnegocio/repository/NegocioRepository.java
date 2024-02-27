package com.negocio.msvcnegocio.repository;

import com.negocio.msvcnegocio.models.entity.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NegocioRepository extends JpaRepository<Negocio,Long> {

}

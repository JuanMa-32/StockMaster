package com.stockmaster.msvccliente.repository;

import com.stockmaster.msvccliente.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

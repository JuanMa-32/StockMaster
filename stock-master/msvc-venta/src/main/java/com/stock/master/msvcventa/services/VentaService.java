package com.stock.master.msvcventa.services;

import com.stock.master.msvcventa.models.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta save(Venta venta);
    List<Venta> findAll();
    Optional<Venta> findById(String id);
    Optional<Venta> findByIdUsuarioAndCliente(String id);
    List<Venta> findAllUsuarioAndCliente();
    void eliminarVenta(String id);



}

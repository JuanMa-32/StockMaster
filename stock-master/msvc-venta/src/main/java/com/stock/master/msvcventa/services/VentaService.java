package com.stock.master.msvcventa.services;

import com.stock.master.msvcventa.models.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    Venta save(Venta venta,Long idNegocio);
    List<Venta> findAll(List<String> ids);
    Optional<Venta> findById(String id);
    Optional<Venta> findByIdUsuarioAndCliente(String id);
    List<Venta> findAllUsuarioAndCliente(List<String> ids);
    void eliminarVenta(String id);
    List<Venta> ventasAll();
    void actualizarVenta(Venta venta);


}

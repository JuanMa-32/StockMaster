package com.stock.master.msvcventa.repositories;

import com.stock.master.msvcventa.models.entity.Venta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VentaRepository extends MongoRepository<Venta,String> {
}

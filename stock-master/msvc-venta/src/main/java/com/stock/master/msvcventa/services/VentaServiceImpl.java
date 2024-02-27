package com.stock.master.msvcventa.services;

import com.stock.master.msvcventa.clients.ClienteClientRest;
import com.stock.master.msvcventa.clients.NegocioClientRest;
import com.stock.master.msvcventa.clients.ProductoClientRest;
import com.stock.master.msvcventa.clients.UsuarioClientRest;
import com.stock.master.msvcventa.models.entity.Venta;
import com.stock.master.msvcventa.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    private ProductoClientRest productoClientRest;
    @Autowired
    private VentaRepository repository;
    @Autowired
    private UsuarioClientRest usuarioClient;
    @Autowired
    private ClienteClientRest clienteClient;
    @Autowired
    private ProductoClientRest productoClient;
    @Autowired
    private NegocioClientRest negocioClientRest;

    @Override
    public Venta save(Venta venta,Long idNegocio) {
        Venta ventaDb = repository.save(venta);
        negocioClientRest.agregarVenta(ventaDb.getId(),idNegocio);
        usuarioClient.nuevaVenta(venta.getIdUsuario(), venta.getTotal());

        productoClient.restarStock(venta.getProductos());
        return ventaDb;

    }

    @Override
    public List<Venta> findAll(List<String> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public Optional<Venta> findById(String id) {
        return  repository.findById(id);
    }

    @Override
    public Optional<Venta> findByIdUsuarioAndCliente(String id) {
        Optional<Venta> o = repository.findById(id);
        if (o.isPresent()){
            Venta venta = o.get();
            if(venta.getIdUsuario() != null){
                venta.setUsuario(usuarioClient.detalle(venta.getIdUsuario()));
            }
            if(venta.getIdCliente() >0){
                venta.setCliente(clienteClient.findById(venta.getIdCliente()));
            }
            return Optional.of(venta);
        }
        return Optional.empty();
    }

    @Override
    public List<Venta> findAllUsuarioAndCliente(List<String>ids) {
        List<Venta> v = repository.findAllById(ids);
        for (Venta venta: v) {
            if(venta.getIdUsuario() != null){
                venta.setUsuario(usuarioClient.detalle(venta.getIdUsuario()));
            }
            if(venta.getIdCliente() > 0 ){
                venta.setCliente(clienteClient.findById(venta.getIdCliente()));
            }
        }
       return v;
    } 

    @Override
    public void eliminarVenta(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<Venta> ventasAll() {
        return repository.findAll();
    }

    @Override
    public void actualizarVenta(Venta venta) {
        repository.save(venta);
    }
}

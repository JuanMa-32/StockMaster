package com.stock.master.msvcventa.services;

import com.stock.master.msvcventa.clients.ClienteClientRest;
import com.stock.master.msvcventa.clients.ProductoClientRest;
import com.stock.master.msvcventa.clients.UsuarioClientRest;
import com.stock.master.msvcventa.models.entity.Venta;
import com.stock.master.msvcventa.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService{
    @Autowired
    private VentaRepository repository;
    @Autowired
    private UsuarioClientRest usuarioClient;
    @Autowired
    private ClienteClientRest clienteClient;
    @Autowired
    private ProductoClientRest productoClient;

    @Override
    public Venta save(Venta venta) {
        usuarioClient.nuevaVenta(venta.getIdUsuario(), venta.getTotal());
        return repository.save(venta);
    }

    @Override
    public List<Venta> findAll() {
        return repository.findAll();
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
            if(venta.getIdCliente() != null){

                venta.setCliente(clienteClient.findById(venta.getIdCliente()));
            }
            return Optional.of(venta);
        }
        return Optional.empty();
    }

    @Override
    public List<Venta> findAllUsuarioAndCliente() {
        List<Venta> v = repository.findAll();
        for (Venta venta: v) {
            if(venta.getIdUsuario() != null){
                venta.setUsuario(usuarioClient.detalle(venta.getIdUsuario()));
            }
            if(venta.getIdCliente() != null){
                venta.setCliente(clienteClient.findById(venta.getIdCliente()));
            }
        }
       return v;
    } 

    @Override
    public void eliminarVenta(String id) {
        repository.deleteById(id);
    }
}

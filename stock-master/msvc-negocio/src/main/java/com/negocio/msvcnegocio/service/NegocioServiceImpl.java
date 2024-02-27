package com.negocio.msvcnegocio.service;

import com.negocio.msvcnegocio.clients.ClienteClientRest;
import com.negocio.msvcnegocio.clients.ProductoClientRest;
import com.negocio.msvcnegocio.clients.UsuarioClientRest;
import com.negocio.msvcnegocio.clients.VentaClientRest;
import com.negocio.msvcnegocio.models.Cliente;
import com.negocio.msvcnegocio.models.Producto;
import com.negocio.msvcnegocio.models.Usuario;
import com.negocio.msvcnegocio.models.Venta;
import com.negocio.msvcnegocio.models.entity.Negocio;
import com.negocio.msvcnegocio.repository.NegocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NegocioServiceImpl implements NegocioService {
    @Autowired
    private UsuarioClientRest usuarioClientRest;
    @Autowired
    private NegocioRepository negocioRepository;
    @Autowired
    private ClienteClientRest clienteRest;
    @Autowired
    private ProductoClientRest productoClientRest;
    @Autowired
    private VentaClientRest ventaClientRest;

    @Override
    public List<Negocio> findAll() {
        return negocioRepository.findAll();
    }

    @Override
    public Optional<Negocio> findById(Long id) {
        Optional<Negocio> o = negocioRepository.findById(id);
        if (o.isPresent()) {
            Negocio negocio = o.get();
            if (negocio.getIdUsuarios() != null) {
                List<Usuario> usuarios = usuarioClientRest.findAll(negocio.getIdUsuarios());
                negocio.setUsuarios(usuarios);
            }
            if (negocio.getIdClientes() != null && !negocio.getIdClientes().isEmpty() ) {
                List<Cliente> clientes = clienteRest.findAll(negocio.getIdClientes());
                negocio.setClientes(clientes);
            }
            if (negocio.getIdProductos() != null && !negocio.getIdProductos().isEmpty()) {
                List<Producto> productos = productoClientRest.findAll(negocio.getIdProductos());
                negocio.setProductos(productos);
            }
            if (negocio.getIdVentas() != null) {
                List<Venta> ventas = ventaClientRest.findAll(negocio.getIdVentas());
                negocio.setVentas(ventas);
            }
            return Optional.of(negocio);
        }
        return Optional.empty();
    }


    @Override
    public Usuario guardarDueno(Usuario usuario, Long idNegocio) {
        return usuarioClientRest.guardarUsuario(usuario, idNegocio);
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

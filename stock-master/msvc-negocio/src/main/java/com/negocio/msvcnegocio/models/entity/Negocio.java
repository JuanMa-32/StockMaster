package com.negocio.msvcnegocio.models.entity;

import com.negocio.msvcnegocio.models.Cliente;
import com.negocio.msvcnegocio.models.Producto;
import com.negocio.msvcnegocio.models.Usuario;
import com.negocio.msvcnegocio.models.Venta;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Entity
@Table(name = "negocios")
@Data
public class Negocio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreComercio;
    private String nombreResponsable;
    private String cedulaIdentidad;
    private Long celular;
    private String email;
    private String direccion;
    private String complemento;
    private String informacion;


    private List<Long>idClientes;
    private List<Long>idUsuarios;
    private List<Long>idProductos;
    private List<String>idVentas;
    @Transient
    private List<Usuario> usuarios;
    @Transient
    private List<Cliente> Clientes;
    @Transient
    private List<Producto> productos;
    @Transient
    private List<Venta> ventas;
    public void addProducto(Long idProducto){
        if (this.idProductos == null) {
            this.idProductos = new ArrayList<>();
        }
        this.idProductos.add(idProducto);
    }
    public void agregarUsuario(Long idUsuario){
        if (this.idUsuarios == null) {
            this.idUsuarios = new ArrayList<>();
        }
        idUsuarios.add(idUsuario);
    }
    public void agregarCliente(Long idCliente){
        if (this.idClientes == null) {
            this.idClientes = new ArrayList<>();
        }
        this.idClientes.add(idCliente);
    }
    public void agregarVenta(String idVenta){
        if (this.idVentas == null) {
            this.idVentas = new ArrayList<>();
        }
        this.idVentas.add(idVenta);
    }
    public void eliminarIdCliente(Long id) {
        Iterator<Long> iterator = idClientes.iterator();
        while (iterator.hasNext()) {
            Long clienteId = iterator.next();
            if (clienteId.equals(id)) {
                iterator.remove();
                break;
            }
        }
    }
    public void eliminarIdProducto(Long id) {
        Iterator<Long> iterator = idProductos.iterator();
        while (iterator.hasNext()) {
            Long productoId = iterator.next();
            if (productoId.equals(id)) {
                iterator.remove();
                break;
            }
        }
    }


}

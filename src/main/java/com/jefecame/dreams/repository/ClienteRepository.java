package com.jefecame.dreams.repository;

import com.jefecame.dreams.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar los datos de los clientes.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ClienteRepository {
    
    private List<Cliente> clientes;
    
    /**
     * Constructor que inicializa la lista de clientes.
     */
    public ClienteRepository() {
        this.clientes = new ArrayList<>();
    }
    
    /**
     * Guarda un cliente en el repositorio.
     * 
     * @param cliente cliente a guardar
     */
    public void guardar(Cliente cliente) {
        if (cliente != null) {
            // Verificar si ya existe un cliente con el mismo ID
            Optional<Cliente> clienteExistente = clientes.stream()
                    .filter(c -> c.getId() == cliente.getId())
                    .findFirst();
            
            if (clienteExistente.isPresent()) {
                // Actualizar cliente existente
                int index = clientes.indexOf(clienteExistente.get());
                clientes.set(index, cliente);
            } else {
                // Agregar nuevo cliente
                clientes.add(cliente);
            }
        }
    }
    
    /**
     * Elimina un cliente del repositorio por su ID.
     * 
     * @param id identificador del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminar(int id) {
        return clientes.removeIf(cliente -> cliente.getId() == id);
    }
    
    /**
     * Busca un cliente por su ID.
     * 
     * @param id identificador del cliente a buscar
     * @return el cliente encontrado o null si no existe
     */
    public Cliente buscarPorId(int id) {
        return clientes.stream()
                .filter(cliente -> cliente.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todos los clientes del repositorio.
     * 
     * @return lista de todos los clientes
     */
    public List<Cliente> obtenerTodos() {
        return new ArrayList<>(clientes);
    }
    
    /**
     * Obtiene el número total de clientes en el repositorio.
     * 
     * @return número total de clientes
     */
    public int contarClientes() {
        return clientes.size();
    }
    
    /**
     * Obtiene todos los clientes activos.
     * 
     * @return lista de clientes activos
     */
    public List<Cliente> obtenerClientesActivos() {
        return clientes.stream()
                .filter(Cliente::isActivo)
                .toList();
    }
}

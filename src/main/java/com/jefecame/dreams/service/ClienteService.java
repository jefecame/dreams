package com.jefecame.dreams.service;

import com.jefecame.dreams.model.Cliente;
import com.jefecame.dreams.repository.ClienteRepository;
import java.util.List;

/**
 * Servicio para la gestión de clientes.
 * Contiene la lógica de negocio relacionada con los clientes.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ClienteService {
    
    private ClienteRepository clienteRepository;
    private static int contadorId = 1;
    
    /**
     * Constructor que inicializa el servicio con un repositorio de clientes.
     * 
     * @param clienteRepository repositorio de clientes a utilizar
     */
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    
    /**
     * Constructor por defecto que inicializa el repositorio.
     */
    public ClienteService() {
        this.clienteRepository = new ClienteRepository();
    }
    
    /**
     * Agrega un nuevo cliente al sistema.
     * 
     * @param nombre nombre del cliente
     * @param email email del cliente
     * @return el cliente creado
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public Cliente agregarCliente(String nombre, String email) {
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email del cliente no puede estar vacío");
        }
        
        if (!esEmailValido(email)) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
        
        // Verificar si ya existe un cliente con el mismo email
        List<Cliente> clientesExistentes = clienteRepository.obtenerTodos();
        boolean emailExiste = clientesExistentes.stream()
                .anyMatch(cliente -> cliente.getEmail().equalsIgnoreCase(email.trim()));
        
        if (emailExiste) {
            throw new IllegalArgumentException("Ya existe un cliente con este email");
        }
        
        // Crear y guardar el nuevo cliente
        Cliente nuevoCliente = new Cliente(contadorId++, nombre.trim(), email.trim());
        clienteRepository.guardar(nuevoCliente);
        
        return nuevoCliente;
    }
    
    /**
     * Elimina un cliente del sistema.
     * 
     * @param id identificador del cliente a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarCliente(int id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente != null) {
            return clienteRepository.eliminar(id);
        }
        return false;
    }
    
    /**
     * Busca un cliente por su identificador.
     * 
     * @param id identificador del cliente a buscar
     * @return el cliente encontrado o null si no existe
     */
    public Cliente buscarCliente(int id) {
        return clienteRepository.buscarPorId(id);
    }
    
    /**
     * Obtiene todos los clientes del sistema.
     * 
     * @return lista de todos los clientes
     */
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.obtenerTodos();
    }
    
    /**
     * Obtiene todos los clientes activos del sistema.
     * 
     * @return lista de clientes activos
     */
    public List<Cliente> obtenerClientesActivos() {
        return clienteRepository.obtenerClientesActivos();
    }
    
    /**
     * Actualiza la información de un cliente.
     * 
     * @param id identificador del cliente a actualizar
     * @param nombre nuevo nombre del cliente
     * @param email nuevo email del cliente
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public boolean actualizarCliente(int id, String nombre, String email) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente == null) {
            return false;
        }
        
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        }
        
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email del cliente no puede estar vacío");
        }
        
        if (!esEmailValido(email)) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
        
        // Verificar si el email ya está en uso por otro cliente
        List<Cliente> clientesExistentes = clienteRepository.obtenerTodos();
        boolean emailExiste = clientesExistentes.stream()
                .anyMatch(c -> c.getId() != id && c.getEmail().equalsIgnoreCase(email.trim()));
        
        if (emailExiste) {
            throw new IllegalArgumentException("Ya existe otro cliente con este email");
        }
        
        // Actualizar información
        cliente.actualizarInformacion(nombre.trim(), email.trim(), cliente.isActivo());
        clienteRepository.guardar(cliente);
        
        return true;
    }
    
    /**
     * Desactiva un cliente (soft delete).
     * 
     * @param id identificador del cliente a desactivar
     * @return true si se desactivó correctamente, false en caso contrario
     */
    public boolean desactivarCliente(int id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente != null) {
            cliente.setActivo(false);
            clienteRepository.guardar(cliente);
            return true;
        }
        return false;
    }
    
    /**
     * Reactiva un cliente.
     * 
     * @param id identificador del cliente a reactivar
     * @return true si se reactivó correctamente, false en caso contrario
     */
    public boolean reactivarCliente(int id) {
        Cliente cliente = clienteRepository.buscarPorId(id);
        if (cliente != null) {
            cliente.setActivo(true);
            clienteRepository.guardar(cliente);
            return true;
        }
        return false;
    }
    
    /**
     * Valida si un email tiene un formato correcto.
     * 
     * @param email email a validar
     * @return true si el formato es válido, false en caso contrario
     */
    private boolean esEmailValido(String email) {
        if (email == null) return false;
        
        // Validación básica de email
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(regex) && email.contains("@") && email.contains(".");
    }
}

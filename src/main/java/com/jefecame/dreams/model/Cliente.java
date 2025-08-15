package com.jefecame.dreams.model;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Clase Cliente: Representa a un cliente de la tienda.
 * Implementa encapsulamiento manteniendo los atributos privados
 * y proporcionando métodos de acceso controlado.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Cliente {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$"
    );
    
    private final UUID id;
    private String nombre;
    private String email;
    private boolean activo;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaActualizacion;

    /**
     * Constructor para crear un nuevo cliente.
     * 
     * @param nombre el nombre del cliente (no puede ser null ni vacío)
     * @param email el email del cliente (debe tener formato válido)
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public Cliente(String nombre, String email) {
        validarNombre(nombre);
        validarEmail(email);
        
        this.id = UUID.randomUUID();
        this.nombre = nombre.trim();
        this.email = email.trim().toLowerCase();
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Getters para acceso controlado a los datos
    
    /**
     * Obtiene el ID único del cliente.
     * 
     * @return el UUID del cliente
     */
    public UUID getId() {
        return id;
    }

    /**
     * Obtiene el nombre del cliente.
     * 
     * @return el nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el email del cliente.
     * 
     * @return el email del cliente
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Verifica si el cliente está activo.
     * 
     * @return true si el cliente está activo, false en caso contrario
     */
    public boolean isActivo() {
        return activo;
    }
    
    /**
     * Obtiene la fecha de creación del cliente.
     * 
     * @return la fecha de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Obtiene la fecha de la última actualización del cliente.
     * 
     * @return la fecha de última actualización
     */
    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    // Setters con validación
    
    /**
     * Actualiza el nombre del cliente.
     * 
     * @param nombre el nuevo nombre (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el nombre no es válido
     */
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Actualiza el email del cliente.
     * 
     * @param email el nuevo email (debe tener formato válido)
     * @throws IllegalArgumentException si el email no es válido
     */
    public void setEmail(String email) {
        validarEmail(email);
        this.email = email.trim().toLowerCase();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Establece el estado activo/inactivo del cliente.
     * 
     * @param activo true para activar, false para desactivar
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Método para actualizar la información del cliente de forma transaccional.
     * 
     * @param nombre el nuevo nombre
     * @param email el nuevo email
     * @param activo el nuevo estado
     * @throws IllegalArgumentException si algún parámetro no es válido
     */
    public void actualizarInformacion(String nombre, String email, boolean activo) {
        // Validar todos los parámetros antes de actualizar
        validarNombre(nombre);
        validarEmail(email);
        
        // Actualizar todos los campos de una vez
        this.nombre = nombre.trim();
        this.email = email.trim().toLowerCase();
        this.activo = activo;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    // Métodos de validación privados
    
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede ser null o vacío");
        }
        if (nombre.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre del cliente debe tener al menos 2 caracteres");
        }
        if (nombre.trim().length() > 100) {
            throw new IllegalArgumentException("El nombre del cliente no puede exceder 100 caracteres");
        }
    }
    
    private void validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email del cliente no puede ser null o vacío");
        }
        if (!EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("El formato del email no es válido");
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return Objects.equals(id, cliente.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Cliente{id=%s, nombre='%s', email='%s', activo=%s, fechaCreacion=%s}",
                id, nombre, email, activo, fechaCreacion);
    }
}

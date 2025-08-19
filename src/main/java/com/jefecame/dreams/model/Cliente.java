package com.jefecame.dreams.model;

/**
 * Clase que representa un cliente en el sistema Dreams.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Cliente {
    
    private int id;
    private String nombre;
    private String email;
    private boolean activo;
    
    /**
     * Constructor para crear un nuevo cliente.
     * 
     * @param id identificador único del cliente
     * @param nombre nombre del cliente
     * @param email email del cliente
     */
    public Cliente(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = true; // Por defecto, un cliente nuevo está activo
    }
    
    /**
     * Obtiene el identificador del cliente.
     * 
     * @return el id del cliente
     */
    public int getId() {
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
     * Establece el nombre del cliente.
     * 
     * @param nombre nuevo nombre del cliente
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece el email del cliente.
     * 
     * @param email nuevo email del cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Establece el estado activo del cliente.
     * 
     * @param activo true para activar el cliente, false para desactivarlo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * Actualiza la información completa del cliente.
     * 
     * @param nombre nuevo nombre del cliente
     * @param email nuevo email del cliente
     * @param activo nuevo estado del cliente
     */
    public void actualizarInformacion(String nombre, String email, boolean activo) {
        this.nombre = nombre;
        this.email = email;
        this.activo = activo;
    }
}

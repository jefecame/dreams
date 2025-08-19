package com.jefecame.dreams.model;

/**
 * Clase que representa una tienda en el sistema Dreams.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Tienda {
    
    private int id;
    private String nombre;
    private String ubicacion;
    private String telefono;
    
    /**
     * Constructor para crear una nueva tienda.
     * 
     * @param id identificador único de la tienda
     * @param nombre nombre de la tienda
     * @param ubicacion ubicación de la tienda
     * @param telefono teléfono de contacto de la tienda
     */
    public Tienda(int id, String nombre, String ubicacion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.telefono = telefono;
    }
    
    /**
     * Obtiene el identificador de la tienda.
     * 
     * @return el id de la tienda
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre de la tienda.
     * 
     * @return el nombre de la tienda
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la ubicación de la tienda.
     * 
     * @return la ubicación de la tienda
     */
    public String getUbicacion() {
        return ubicacion;
    }
    
    /**
     * Obtiene el teléfono de la tienda.
     * 
     * @return el teléfono de la tienda
     */
    public String getTelefono() {
        return telefono;
    }
}

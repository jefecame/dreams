package com.jefecame.dreams.model;

import java.util.UUID;

/**
 * Clase Cliente: Representa a un cliente de la tienda.
 * Se aplica encapsulamiento al mantener los atributos privados.
 */
class Cliente {
    private final UUID id;
    private String nombre;
    private String email;
    private boolean activo;

    public Cliente(String nombre, String email) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.email = email;
        this.activo = true;
    }

    // Getters para acceso controlado a los datos
    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
    public boolean isActivo() {
        return activo;
    }

    /**
     * Método para actualizar la información del cliente.
     */
    public void actualizarInformacion(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Cliente [ID=" + id + ", Nombre=" + nombre + ", Email=" + email + "]";
    }
}
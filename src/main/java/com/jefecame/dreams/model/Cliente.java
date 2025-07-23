package com.jefecame.dreams.model;

import java.util.UUID;

public class Cliente {
    private UUID id;
    private String nombre;
    private String email;
    private boolean activo;

    public Cliente(UUID id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.activo = true;
    }

    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public boolean getActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public void actualizarInformacion(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
package com.jefecame.dreams.model;

import java.math.BigDecimal;

public class Producto {
    protected int id;
    protected String nombre;
    protected BigDecimal precio;
    protected String categoria;
    protected int stock;
    protected boolean activo;

    public Producto(String nombre, BigDecimal precio, String categoria, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.stock = stock;
        this.activo = true;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio.doubleValue(); }
    public String getCategoria() { return categoria; }
    public int getStock() { return stock; }
    public boolean getActivo() { return activo; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = BigDecimal.valueOf(precio); }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setStock(int stock) { this.stock = stock; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }

    public boolean validarDisponibilidad(int cantidadRequerida) {
        return this.stock >= cantidadRequerida;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoria='" + categoria + '\'' +
                ", stock=" + stock +
                ", activo=" + activo +
                '}';
    }
    
}
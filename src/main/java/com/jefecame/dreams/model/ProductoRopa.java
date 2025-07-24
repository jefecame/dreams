package com.jefecame.dreams.model;

import java.math.BigDecimal;

public class ProductoRopa extends Producto {

    private String talla;
    private String color;

    public ProductoRopa(String id, String nombre, BigDecimal precio, String categoria, int stock, boolean activo, String talla, String color) {
        super(nombre, precio, categoria, stock);
        this.talla = talla;
        this.color = color;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String mostrarDetalles() {
        return "ProductoRopa{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", categoria='" + getCategoria() + '\'' +
                ", stock=" + getStock() +
                ", activo=" + isActivo() +
                ", talla='" + talla + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
    
}

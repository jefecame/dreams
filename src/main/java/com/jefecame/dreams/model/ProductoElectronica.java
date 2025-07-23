package com.jefecame.dreams.model;

import java.math.BigDecimal;

public class ProductoElectronica extends Producto {

    private String marca;
    private String modelo;

    public ProductoElectronica(int id, String nombre, BigDecimal precio, String categoria, int stock, boolean activo, String marca, String modelo) {
        super(nombre, precio, categoria, stock);
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "ProductoElectronica{" +
                "id='" + getId() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", stock=" + getStock() +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
    
}

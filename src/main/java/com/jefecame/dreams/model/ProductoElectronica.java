package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Representa un producto de tipo Electrónica.
 * Hereda de Producto y añade atributos específicos como marca y modelo.
 */
public class ProductoElectronica extends Producto {

    private String marca;
    private String modelo;

    public ProductoElectronica(int id, String nombre, BigDecimal precio, int stock, boolean activo, String marca, String modelo) {
        super(id, nombre, precio, "Electrónica", stock, activo);
        this.marca = marca;
        this.modelo = modelo;
    }

    // --- Getters y Setters específicos ---

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
    public String mostrarDetalles() {
        return "ProductoElectronica{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", precio=" + getPrecio() +
                ", categoria='" + getCategoria() + '\'' +
                ", stock=" + getStock() +
                ", activo=" + isActivo() +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                '}';
    }
}

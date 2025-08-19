package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Clase que representa un producto de electrónica en el sistema Dreams.
 * Extiende la clase abstracta Producto.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoElectronica extends Producto {
    
    private String marca;
    private String modelo;
    
    /**
     * Constructor para crear un nuevo producto de electrónica.
     * 
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param stock cantidad en stock del producto
     * @param marca marca del producto electrónico
     * @param modelo modelo del producto electrónico
     */
    public ProductoElectronica(String nombre, BigDecimal precio, int stock, String marca, String modelo) {
        super(nombre, precio, stock);
        this.marca = marca;
        this.modelo = modelo;
    }
    
    /**
     * Obtiene la marca del producto electrónico.
     * 
     * @return la marca del producto
     */
    public String getMarca() {
        return marca;
    }
    
    /**
     * Obtiene el modelo del producto electrónico.
     * 
     * @return el modelo del producto
     */
    public String getModelo() {
        return modelo;
    }
    
    /**
     * Establece la marca del producto electrónico.
     * 
     * @param marca nueva marca del producto
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    /**
     * Establece el modelo del producto electrónico.
     * 
     * @param modelo nuevo modelo del producto
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    /**
     * Muestra los detalles específicos del producto electrónico.
     * 
     * @return cadena con los detalles del producto electrónico
     */
    @Override
    public String mostrarDetalles() {
        return String.format("Producto Electrónico: %s - Marca: %s, Modelo: %s, Precio: $%.2f, Stock: %d", 
                           nombre, marca, modelo, precio, stock);
    }
}

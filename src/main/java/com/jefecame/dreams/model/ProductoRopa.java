package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Clase que representa un producto de ropa en el sistema Dreams.
 * Extiende la clase abstracta Producto.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoRopa extends Producto {
    
    private String talla;
    private String color;
    
    /**
     * Constructor para crear un nuevo producto de ropa.
     * 
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param stock cantidad en stock del producto
     * @param talla talla del producto de ropa
     * @param color color del producto de ropa
     */
    public ProductoRopa(String nombre, BigDecimal precio, int stock, String talla, String color) {
        super(nombre, precio, stock);
        this.talla = talla;
        this.color = color;
    }
    
    /**
     * Obtiene la talla del producto de ropa.
     * 
     * @return la talla del producto
     */
    public String getTalla() {
        return talla;
    }
    
    /**
     * Obtiene el color del producto de ropa.
     * 
     * @return el color del producto
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Establece la talla del producto de ropa.
     * 
     * @param talla nueva talla del producto
     */
    public void setTalla(String talla) {
        this.talla = talla;
    }
    
    /**
     * Establece el color del producto de ropa.
     * 
     * @param color nuevo color del producto
     */
    public void setColor(String color) {
        this.color = color;
    }
    
    /**
     * Muestra los detalles específicos del producto de ropa.
     * 
     * @return cadena con los detalles del producto de ropa
     */
    @Override
    public String mostrarDetalles() {
        return String.format("Producto de Ropa: %s - Talla: %s, Color: %s, Precio: $%.2f, Stock: %d", nombre, talla, color, precio, stock);
    }
}

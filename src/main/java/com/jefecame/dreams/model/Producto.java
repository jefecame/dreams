package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Clase abstracta que representa un producto en el sistema Dreams.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public abstract class Producto {
    
    protected int id;
    protected String nombre;
    protected BigDecimal precio;
    protected int stock;
    protected boolean activo;
    private static int contadorId = 1;
    
    /**
     * Constructor para crear un nuevo producto.
     * 
     * @param nombre nombre del producto
     * @param precio precio del producto
     * @param stock cantidad en stock del producto
     */
    public Producto(String nombre, BigDecimal precio, int stock) {
        this.id = contadorId++;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.activo = true; // Por defecto, un producto nuevo está activo
    }
    
    /**
     * Obtiene el identificador del producto.
     * 
     * @return el id del producto
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del producto.
     * 
     * @return el nombre del producto
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene el precio del producto.
     * 
     * @return el precio del producto
     */
    public BigDecimal getPrecio() {
        return precio;
    }
    
    /**
     * Obtiene el stock del producto.
     * 
     * @return la cantidad en stock del producto
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Verifica si el producto está activo.
     * 
     * @return true si el producto está activo, false en caso contrario
     */
    public boolean isActivo() {
        return activo;
    }
    
    /**
     * Establece el nombre del producto.
     * 
     * @param nombre nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece el precio del producto.
     * 
     * @param precio nuevo precio del producto
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    
    /**
     * Establece la categoría del producto.
     * Nota: Este método está definido en la UML pero no hay atributo categoría.
     * Se mantiene por compatibilidad con el diseño.
     * 
     * @param categoria nueva categoría del producto
     */
    public void setCategoria(String categoria) {
        // Implementación vacía - método definido en UML
    }
    
    /**
     * Establece el stock del producto.
     * 
     * @param stock nueva cantidad en stock del producto
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Establece el estado activo del producto.
     * 
     * @param activo true para activar el producto, false para desactivarlo
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    /**
     * Actualiza el stock del producto sumando o restando la cantidad especificada.
     * 
     * @param cantidad cantidad a agregar (positiva) o quitar (negativa) del stock
     */
    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
        if (this.stock < 0) {
            this.stock = 0;
        }
    }
    
    /**
     * Valida si hay suficiente stock para satisfacer la cantidad requerida.
     * 
     * @param cantidadRequerida cantidad que se desea verificar
     * @return true si hay suficiente stock, false en caso contrario
     */
    public boolean validarDisponibilidad(Integer cantidadRequerida) {
        return this.stock >= cantidadRequerida && this.activo;
    }
    
    /**
     * Método abstracto que debe ser implementado por las clases hijas
     * para mostrar detalles específicos del producto.
     * 
     * @return cadena con los detalles del producto
     */
    public abstract String mostrarDetalles();
}

package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Clase abstracta Producto: Base para todos los artículos.
 * Demuestra Abstracción y es la base para la Herencia.
 */
abstract class Producto {
    private static int contadorId = 0;
    protected int id;
    protected String nombre;
    protected BigDecimal precio;
    protected int stock;

    public Producto(String nombre, BigDecimal precio, int stock) {
        this.id = ++contadorId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }

    /**
     * Actualiza el stock. Positivo para añadir, negativo para reducir.
     */
    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }

    /**
     * Valida si hay suficiente stock para una cantidad requerida.
     */
    public boolean validarDisponibilidad(int cantidadRequerida) {
        return this.stock >= cantidadRequerida;
    }
    
    // Método abstracto para ser implementado por las subclases (Polimorfismo)
    public abstract String mostrarDetalles();
}
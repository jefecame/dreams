package com.jefecame.dreams.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Clase abstracta Producto: Base para todos los artículos del sistema.
 * Demuestra Abstracción y sirve como base para la Herencia.
 * Implementa encapsulamiento y proporciona funcionalidad común para todos los productos.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public abstract class Producto {
    
    private static int contadorId = 0;
    
    protected final int id;
    protected String nombre;
    protected BigDecimal precio;
    protected CategoriaProducto categoria;
    protected int stock;
    protected boolean activo;
    protected final LocalDateTime fechaCreacion;
    protected LocalDateTime fechaUltimaActualizacion;

    /**
     * Constructor para crear un nuevo producto.
     * 
     * @param nombre el nombre del producto (no puede ser null ni vacío)
     * @param precio el precio del producto (debe ser positivo)
     * @param categoria la categoría del producto
     * @param stock la cantidad inicial en stock (debe ser no negativo)
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public Producto(String nombre, BigDecimal precio, CategoriaProducto categoria, int stock) {
        validarNombre(nombre);
        validarPrecio(precio);
        validarStock(stock);
        
        this.id = ++contadorId;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.categoria = categoria != null ? categoria : CategoriaProducto.OTROS;
        this.stock = stock;
        this.activo = true;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Getters
    
    /**
     * Obtiene el ID único del producto.
     * 
     * @return el ID del producto
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
     * Obtiene la categoría del producto.
     * 
     * @return la categoría del producto
     */
    public CategoriaProducto getCategoria() {
        return categoria;
    }
    
    /**
     * Obtiene la cantidad en stock del producto.
     * 
     * @return la cantidad en stock
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
     * Obtiene la fecha de creación del producto.
     * 
     * @return la fecha de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Obtiene la fecha de la última actualización del producto.
     * 
     * @return la fecha de última actualización
     */
    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    // Setters con validación
    
    /**
     * Actualiza el nombre del producto.
     * 
     * @param nombre el nuevo nombre (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el nombre no es válido
     */
    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Actualiza el precio del producto.
     * 
     * @param precio el nuevo precio (debe ser positivo)
     * @throws IllegalArgumentException si el precio no es válido
     */
    public void setPrecio(BigDecimal precio) {
        validarPrecio(precio);
        this.precio = precio;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Actualiza la categoría del producto.
     * 
     * @param categoria la nueva categoría
     */
    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria != null ? categoria : CategoriaProducto.OTROS;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Actualiza el stock del producto directamente.
     * 
     * @param stock la nueva cantidad en stock (debe ser no negativo)
     * @throws IllegalArgumentException si el stock es negativo
     */
    public void setStock(int stock) {
        validarStock(stock);
        this.stock = stock;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Establece el estado activo/inactivo del producto.
     * 
     * @param activo true para activar, false para desactivar
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Actualiza el stock del producto de forma incremental.
     * Positivo para añadir, negativo para reducir.
     * 
     * @param cantidad la cantidad a sumar/restar del stock actual
     * @throws IllegalArgumentException si el resultado sería negativo
     */
    public void actualizarStock(int cantidad) {
        int nuevoStock = this.stock + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException(
                String.format("No se puede reducir el stock. Stock actual: %d, cantidad solicitada: %d",
                    this.stock, Math.abs(cantidad)));
        }
        this.stock = nuevoStock;
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Valida si hay suficiente stock para una cantidad requerida.
     * 
     * @param cantidadRequerida la cantidad a validar
     * @return true si hay suficiente stock y el producto está activo, false en caso contrario
     */
    public boolean validarDisponibilidad(int cantidadRequerida) {
        return this.activo && this.stock >= cantidadRequerida && cantidadRequerida > 0;
    }
    
    // Métodos de validación privados
    
    private void validarNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede ser null o vacío");
        }
        if (nombre.trim().length() < 2) {
            throw new IllegalArgumentException("El nombre del producto debe tener al menos 2 caracteres");
        }
        if (nombre.trim().length() > 200) {
            throw new IllegalArgumentException("El nombre del producto no puede exceder 200 caracteres");
        }
    }
    
    private void validarPrecio(BigDecimal precio) {
        if (precio == null) {
            throw new IllegalArgumentException("El precio no puede ser null");
        }
        if (precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
    }
    
    private void validarStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return id == producto.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return String.format("Producto{id=%d, nombre='%s', precio=%s, categoria=%s, stock=%d, activo=%s}",
                id, nombre, precio, categoria, stock, activo);
    }
    
    // Método abstracto para ser implementado por las subclases (Polimorfismo)
    /**
     * Muestra los detalles específicos del producto.
     * Este método debe ser implementado por cada subclase.
     * 
     * @return una cadena con los detalles del producto
     */
    public abstract String mostrarDetalles();
}

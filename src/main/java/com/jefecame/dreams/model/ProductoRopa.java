package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * ProductoRopa - Representa un producto de tipo Ropa.
 * Hereda de Producto y añade atributos específicos como talla y color.
 * Demuestra el uso de Herencia y especialización de comportamiento.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoRopa extends Producto {

    private String talla;
    private String color;
    private String material;
    private String temporada;

    /**
     * Constructor para crear un nuevo producto de ropa.
     * 
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     * @param stock la cantidad inicial en stock
     * @param talla la talla del producto
     * @param color el color del producto
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public ProductoRopa(String nombre, BigDecimal precio, int stock, String talla, String color) {
        super(nombre, precio, CategoriaProducto.ROPA, stock);
        validarTalla(talla);
        validarColor(color);
        
        this.talla = talla.trim().toUpperCase();
        this.color = color.trim();
        this.material = "No especificado";
        this.temporada = "Todo el año";
    }
    
    /**
     * Constructor completo para crear un nuevo producto de ropa.
     * 
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     * @param stock la cantidad inicial en stock
     * @param talla la talla del producto
     * @param color el color del producto
     * @param material el material del producto
     * @param temporada la temporada del producto
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public ProductoRopa(String nombre, BigDecimal precio, int stock, 
                       String talla, String color, String material, String temporada) {
        super(nombre, precio, CategoriaProducto.ROPA, stock);
        validarTalla(talla);
        validarColor(color);
        validarMaterial(material);
        validarTemporada(temporada);
        
        this.talla = talla.trim().toUpperCase();
        this.color = color.trim();
        this.material = material.trim();
        this.temporada = temporada.trim();
    }

    // Getters y Setters específicos
    
    /**
     * Obtiene la talla del producto de ropa.
     * 
     * @return la talla del producto
     */
    public String getTalla() {
        return talla;
    }

    /**
     * Actualiza la talla del producto de ropa.
     * 
     * @param talla la nueva talla (no puede ser null ni vacía)
     * @throws IllegalArgumentException si la talla no es válida
     */
    public void setTalla(String talla) {
        validarTalla(talla);
        this.talla = talla.trim().toUpperCase();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
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
     * Actualiza el color del producto de ropa.
     * 
     * @param color el nuevo color (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el color no es válido
     */
    public void setColor(String color) {
        validarColor(color);
        this.color = color.trim();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
    }
    
    /**
     * Obtiene el material del producto de ropa.
     * 
     * @return el material del producto
     */
    public String getMaterial() {
        return material;
    }
    
    /**
     * Actualiza el material del producto de ropa.
     * 
     * @param material el nuevo material
     * @throws IllegalArgumentException si el material no es válido
     */
    public void setMaterial(String material) {
        validarMaterial(material);
        this.material = material.trim();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
    }
    
    /**
     * Obtiene la temporada del producto de ropa.
     * 
     * @return la temporada del producto
     */
    public String getTemporada() {
        return temporada;
    }
    
    /**
     * Actualiza la temporada del producto de ropa.
     * 
     * @param temporada la nueva temporada
     * @throws IllegalArgumentException si la temporada no es válida
     */
    public void setTemporada(String temporada) {
        validarTemporada(temporada);
        this.temporada = temporada.trim();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
    }
    
    // Métodos de validación privados
    
    private void validarTalla(String talla) {
        if (talla == null || talla.trim().isEmpty()) {
            throw new IllegalArgumentException("La talla no puede ser null o vacía");
        }
        String tallaUpper = talla.trim().toUpperCase();
        if (!tallaUpper.matches("^(XS|S|M|L|XL|XXL|XXXL|\\d+|\\d+[.-]\\d+)$")) {
            throw new IllegalArgumentException("La talla debe ser XS, S, M, L, XL, XXL, XXXL o un número válido");
        }
    }
    
    private void validarColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color no puede ser null o vacío");
        }
        if (color.trim().length() < 2) {
            throw new IllegalArgumentException("El color debe tener al menos 2 caracteres");
        }
        if (color.trim().length() > 50) {
            throw new IllegalArgumentException("El color no puede exceder 50 caracteres");
        }
    }
    
    private void validarMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new IllegalArgumentException("El material no puede ser null o vacío");
        }
        if (material.trim().length() > 100) {
            throw new IllegalArgumentException("El material no puede exceder 100 caracteres");
        }
    }
    
    private void validarTemporada(String temporada) {
        if (temporada == null || temporada.trim().isEmpty()) {
            throw new IllegalArgumentException("La temporada no puede ser null o vacía");
        }
        if (temporada.trim().length() > 50) {
            throw new IllegalArgumentException("La temporada no puede exceder 50 caracteres");
        }
    }

    @Override
    public String mostrarDetalles() {
        return String.format(
            "Producto de Ropa:%n" +
            "  ID: %d%n" +
            "  Nombre: %s%n" +
            "  Precio: $%s%n" +
            "  Categoría: %s%n" +
            "  Stock: %d unidades%n" +
            "  Estado: %s%n" +
            "  Talla: %s%n" +
            "  Color: %s%n" +
            "  Material: %s%n" +
            "  Temporada: %s%n" +
            "  Fecha de creación: %s",
            getId(), getNombre(), getPrecio(), getCategoria().getNombre(),
            getStock(), isActivo() ? "Activo" : "Inactivo",
            talla, color, material, temporada, getFechaCreacion()
        );
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProductoRopa{id=%d, nombre='%s', precio=%s, talla='%s', color='%s', material='%s', temporada='%s', stock=%d, activo=%s}",
            getId(), getNombre(), getPrecio(), talla, color, material, temporada, getStock(), isActivo()
        );
    }
}

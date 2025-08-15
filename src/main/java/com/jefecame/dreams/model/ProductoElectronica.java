package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * ProductoElectronica - Representa un producto de tipo Electrónica.
 * Hereda de Producto y añade atributos específicos como marca y modelo.
 * Demuestra el uso de Herencia y especialización de comportamiento.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoElectronica extends Producto {

    private String marca;
    private String modelo;
    private int garantiaMeses;

    /**
     * Constructor para crear un nuevo producto electrónico.
     * 
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     * @param stock la cantidad inicial en stock
     * @param marca la marca del producto
     * @param modelo el modelo del producto
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public ProductoElectronica(String nombre, BigDecimal precio, int stock, String marca, String modelo) {
        super(nombre, precio, CategoriaProducto.ELECTRONICA, stock);
        validarMarca(marca);
        validarModelo(modelo);
        
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.garantiaMeses = 12; // Garantía por defecto de 12 meses
    }
    
    /**
     * Constructor completo para crear un nuevo producto electrónico.
     * 
     * @param nombre el nombre del producto
     * @param precio el precio del producto
     * @param stock la cantidad inicial en stock
     * @param marca la marca del producto
     * @param modelo el modelo del producto
     * @param garantiaMeses los meses de garantía
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public ProductoElectronica(String nombre, BigDecimal precio, int stock, 
                              String marca, String modelo, int garantiaMeses) {
        super(nombre, precio, CategoriaProducto.ELECTRONICA, stock);
        validarMarca(marca);
        validarModelo(modelo);
        validarGarantia(garantiaMeses);
        
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.garantiaMeses = garantiaMeses;
    }

    // Getters y Setters específicos
    
    /**
     * Obtiene la marca del producto electrónico.
     * 
     * @return la marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Actualiza la marca del producto electrónico.
     * 
     * @param marca la nueva marca (no puede ser null ni vacía)
     * @throws IllegalArgumentException si la marca no es válida
     */
    public void setMarca(String marca) {
        validarMarca(marca);
        this.marca = marca.trim();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
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
     * Actualiza el modelo del producto electrónico.
     * 
     * @param modelo el nuevo modelo (no puede ser null ni vacío)
     * @throws IllegalArgumentException si el modelo no es válido
     */
    public void setModelo(String modelo) {
        validarModelo(modelo);
        this.modelo = modelo.trim();
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
    }
    
    /**
     * Obtiene los meses de garantía del producto electrónico.
     * 
     * @return los meses de garantía
     */
    public int getGarantiaMeses() {
        return garantiaMeses;
    }
    
    /**
     * Actualiza los meses de garantía del producto electrónico.
     * 
     * @param garantiaMeses los nuevos meses de garantía (debe ser positivo)
     * @throws IllegalArgumentException si la garantía no es válida
     */
    public void setGarantiaMeses(int garantiaMeses) {
        validarGarantia(garantiaMeses);
        this.garantiaMeses = garantiaMeses;
        this.fechaUltimaActualizacion = java.time.LocalDateTime.now();
    }
    
    // Métodos de validación privados
    
    private void validarMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede ser null o vacía");
        }
        if (marca.trim().length() < 2) {
            throw new IllegalArgumentException("La marca debe tener al menos 2 caracteres");
        }
        if (marca.trim().length() > 50) {
            throw new IllegalArgumentException("La marca no puede exceder 50 caracteres");
        }
    }
    
    private void validarModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede ser null o vacío");
        }
        if (modelo.trim().length() < 1) {
            throw new IllegalArgumentException("El modelo debe tener al menos 1 caracter");
        }
        if (modelo.trim().length() > 100) {
            throw new IllegalArgumentException("El modelo no puede exceder 100 caracteres");
        }
    }
    
    private void validarGarantia(int garantiaMeses) {
        if (garantiaMeses < 0) {
            throw new IllegalArgumentException("La garantía no puede ser negativa");
        }
        if (garantiaMeses > 120) { // Máximo 10 años
            throw new IllegalArgumentException("La garantía no puede exceder 120 meses");
        }
    }

    @Override
    public String mostrarDetalles() {
        return String.format(
            "Producto Electrónico:%n" +
            "  ID: %d%n" +
            "  Nombre: %s%n" +
            "  Precio: $%s%n" +
            "  Categoría: %s%n" +
            "  Stock: %d unidades%n" +
            "  Estado: %s%n" +
            "  Marca: %s%n" +
            "  Modelo: %s%n" +
            "  Garantía: %d meses%n" +
            "  Fecha de creación: %s",
            getId(), getNombre(), getPrecio(), getCategoria().getNombre(),
            getStock(), isActivo() ? "Activo" : "Inactivo",
            marca, modelo, garantiaMeses, getFechaCreacion()
        );
    }
    
    @Override
    public String toString() {
        return String.format(
            "ProductoElectronica{id=%d, nombre='%s', precio=%s, marca='%s', modelo='%s', garantia=%d meses, stock=%d, activo=%s}",
            getId(), getNombre(), getPrecio(), marca, modelo, garantiaMeses, getStock(), isActivo()
        );
    }
}

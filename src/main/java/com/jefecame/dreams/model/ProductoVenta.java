package com.jefecame.dreams.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * ProductoVenta - Representa la relación entre un producto y una venta.
 * Almacena la cantidad y el precio unitario al momento de la venta.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoVenta {
    
    private static int contadorId = 0;
    
    private final int id;
    private int idProducto;
    private int idVenta;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaActualizacion;

    /**
     * Constructor para crear un ProductoVenta.
     * 
     * @param idProducto el ID del producto
     * @param idVenta el ID de la venta
     * @param cantidad la cantidad del producto (debe ser positiva)
     * @param precioUnitario el precio unitario al momento de la venta (debe ser positivo)
     * @throws IllegalArgumentException si los parámetros no son válidos
     */
    public ProductoVenta(int idProducto, int idVenta, int cantidad, BigDecimal precioUnitario) {
        validarIdProducto(idProducto);
        validarIdVenta(idVenta);
        validarCantidad(cantidad);
        validarPrecioUnitario(precioUnitario);
        
        this.id = ++contadorId;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
        this.fechaCreacion = LocalDateTime.now();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Getters
    
    /**
     * Obtiene el ID único del ProductoVenta.
     * 
     * @return el ID del ProductoVenta
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Obtiene el ID del producto.
     * 
     * @return el ID del producto
     */
    public int getIdProducto() { 
        return idProducto; 
    }
    
    /**
     * Obtiene el ID de la venta.
     * 
     * @return el ID de la venta
     */
    public int getIdVenta() { 
        return idVenta; 
    }
    
    /**
     * Obtiene la cantidad del producto.
     * 
     * @return la cantidad del producto
     */
    public int getCantidad() { 
        return cantidad; 
    }
    
    /**
     * Obtiene el precio unitario del producto al momento de la venta.
     * 
     * @return el precio unitario
     */
    public BigDecimal getPrecioUnitario() { 
        return precioUnitario; 
    }
    
    /**
     * Obtiene el subtotal calculado (cantidad * precio unitario).
     * 
     * @return el subtotal
     */
    public BigDecimal getSubtotal() { 
        return subtotal; 
    }
    
    /**
     * Obtiene la fecha de creación del ProductoVenta.
     * 
     * @return la fecha de creación
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Obtiene la fecha de la última actualización.
     * 
     * @return la fecha de última actualización
     */
    public LocalDateTime getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    // Setters con validación
    
    /**
     * Actualiza la cantidad del producto y recalcula el subtotal.
     * 
     * @param cantidad la nueva cantidad (debe ser positiva)
     * @throws IllegalArgumentException si la cantidad no es válida
     */
    public void setCantidad(int cantidad) {
        validarCantidad(cantidad);
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Actualiza el precio unitario y recalcula el subtotal.
     * 
     * @param precioUnitario el nuevo precio unitario (debe ser positivo)
     * @throws IllegalArgumentException si el precio no es válido
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        validarPrecioUnitario(precioUnitario);
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    /**
     * Calcula el subtotal multiplicando cantidad por precio unitario.
     * 
     * @return el subtotal calculado
     */
    public BigDecimal calcularSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad))
                            .setScale(2, RoundingMode.HALF_UP);
    }
    
    /**
     * Actualiza tanto la cantidad como el precio unitario de forma atómica.
     * 
     * @param cantidad la nueva cantidad
     * @param precioUnitario el nuevo precio unitario
     * @throws IllegalArgumentException si algún parámetro no es válido
     */
    public void actualizar(int cantidad, BigDecimal precioUnitario) {
        validarCantidad(cantidad);
        validarPrecioUnitario(precioUnitario);
        
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    // Métodos de validación privados
    
    private void validarIdProducto(int idProducto) {
        if (idProducto <= 0) {
            throw new IllegalArgumentException("El ID del producto debe ser positivo");
        }
    }
    
    private void validarIdVenta(int idVenta) {
        if (idVenta <= 0) {
            throw new IllegalArgumentException("El ID de la venta debe ser positivo");
        }
    }
    
    private void validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
    }
    
    private void validarPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null) {
            throw new IllegalArgumentException("El precio unitario no puede ser null");
        }
        if (precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario debe ser positivo");
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProductoVenta that = (ProductoVenta) obj;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
            "ProductoVenta{id=%d, idProducto=%d, idVenta=%d, cantidad=%d, precioUnitario=%s, subtotal=%s, fechaCreacion=%s}",
            id, idProducto, idVenta, cantidad, precioUnitario, subtotal, fechaCreacion
        );
    }
}

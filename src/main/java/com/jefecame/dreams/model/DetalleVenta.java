package com.jefecame.dreams.model;

import java.math.BigDecimal;

/**
 * Clase que representa un detalle de venta en el sistema Dreams.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class DetalleVenta {
    
    private int id;
    private int idVenta;
    private Producto producto;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private static int contadorId = 1;
    
    /**
     * Constructor para crear un nuevo detalle de venta.
     * 
     * @param idProducto identificador del producto (se obtiene del producto)
     * @param idVenta identificador de la venta
     * @param cantidad cantidad del producto
     * @param precioUnitario precio unitario del producto
     */
    public DetalleVenta(int idProducto, int idVenta, int cantidad, BigDecimal precioUnitario) {
        this.id = contadorId++;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }
    
    /**
     * Constructor alternativo que acepta directamente el objeto Producto.
     * 
     * @param producto producto asociado al detalle
     * @param idVenta identificador de la venta
     * @param cantidad cantidad del producto
     */
    public DetalleVenta(Producto producto, int idVenta, int cantidad) {
        this.id = contadorId++;
        this.producto = producto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = calcularSubtotal();
    }
    
    /**
     * Obtiene el identificador del detalle de venta.
     * 
     * @return el id del detalle de venta
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el identificador del producto.
     * 
     * @return el id del producto
     */
    public int getIdProducto() {
        return producto != null ? producto.getId() : 0;
    }
    
    /**
     * Obtiene el identificador de la venta.
     * 
     * @return el id de la venta
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
     * Obtiene el precio unitario del producto.
     * 
     * @return el precio unitario del producto
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }
    
    /**
     * Obtiene el subtotal del detalle de venta.
     * 
     * @return el subtotal del detalle de venta
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    /**
     * Obtiene el producto asociado al detalle.
     * 
     * @return el producto
     */
    public Producto getProducto() {
        return producto;
    }
    
    /**
     * Establece la cantidad del producto.
     * 
     * @param cantidad nueva cantidad del producto
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }
    
    /**
     * Calcula el subtotal multiplicando cantidad por precio unitario.
     * 
     * @return el subtotal calculado
     */
    public BigDecimal calcularSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }
}

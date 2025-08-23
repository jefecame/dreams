package com.jefecame.dreams.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una venta en el sistema Dreams.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Venta {
    
    private int id;
    private Cliente cliente;
    private List<DetalleVenta> productosVenta;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private EstadoVenta estado;
    private static int contadorId = 1;
    private static final BigDecimal TASA_IMPUESTO = new BigDecimal("0.16"); // 16% IVA
    
    /**
     * Constructor para crear una nueva venta que acepta directamente el objeto Cliente.
     * 
     * @param cliente cliente asociado a la venta
     */
    public Venta(Cliente cliente) {
        this.id = contadorId++;
        this.cliente = cliente;
        this.fechaCreacion = LocalDateTime.now();
        this.productosVenta = new ArrayList<>();
        this.subtotal = BigDecimal.ZERO;
        this.impuestos = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
        this.estado = EstadoVenta.PROCESANDO;
    }
    
    /**
     * Obtiene el identificador de la venta.
     * 
     * @return el id de la venta
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el identificador del cliente.
     * 
     * @return el id del cliente
     */
    public int getIdCliente() {
        return cliente != null ? cliente.getId() : 0;
    }
    
    /**
     * Obtiene el cliente asociado a la venta.
     * 
     * @return el cliente
     */
    public Cliente getCliente() {
        return cliente;
    }
    
    /**
     * Establece el cliente asociado a la venta.
     * 
     * @param cliente el cliente a asociar
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    /**
     * Obtiene la lista de productos vendidos.
     * 
     * @return lista de detalles de venta
     */
    public List<DetalleVenta> getProductosVenta() {
        return new ArrayList<>(productosVenta);
    }
    
    /**
     * Obtiene el subtotal de la venta.
     * 
     * @return el subtotal de la venta
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    /**
     * Obtiene los impuestos de la venta.
     * 
     * @return los impuestos de la venta
     */
    public BigDecimal getImpuestos() {
        return impuestos;
    }
    
    /**
     * Obtiene el total de la venta.
     * 
     * @return el total de la venta
     */
    public BigDecimal getTotal() {
        return total;
    }
    
    /**
     * Obtiene la fecha de creación de la venta.
     * 
     * @return la fecha de creación de la venta
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    /**
     * Obtiene el estado actual de la venta.
     * 
     * @return el estado de la venta
     */
    public EstadoVenta getEstado() {
        return estado;
    }
    
    /**
     * Agrega un producto a la venta.
     * 
     * @param producto producto a agregar
     * @param cantidad cantidad del producto
     * @return true si se agregó correctamente, false en caso contrario
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0 || estado != EstadoVenta.PROCESANDO) {
            return false;
        }
        
        if (!producto.validarDisponibilidad(cantidad)) {
            return false;
        }
        
        DetalleVenta detalle = new DetalleVenta(producto, this.id, cantidad);
        productosVenta.add(detalle);
        
        // Actualizar stock del producto
        producto.actualizarStock(-cantidad);
        
        // Recalcular totales
        calcularTotal();
        
        return true;
    }
    
    /**
     * Remueve un producto de la venta.
     * 
     * @param idProductoVenta identificador del detalle de venta a remover
     * @return true si se removió correctamente, false en caso contrario
     */
    public boolean removerProducto(int idProductoVenta) {
        if (estado != EstadoVenta.PROCESANDO) {
            return false;
        }
        
        DetalleVenta detalleARemover = null;
        for (DetalleVenta detalle : productosVenta) {
            if (detalle.getId() == idProductoVenta) {
                detalleARemover = detalle;
                break;
            }
        }
        
        if (detalleARemover != null) {
            // Devolver stock al producto
            if (detalleARemover.getProducto() != null) {
                detalleARemover.getProducto().actualizarStock(detalleARemover.getCantidad());
            }
            
            productosVenta.remove(detalleARemover);
            calcularTotal();
            return true;
        }
        
        return false;
    }
    
    /**
     * Calcula el total de la venta incluyendo impuestos.
     * 
     * @return true si se calculó correctamente
     */
    public boolean calcularTotal() {
        this.subtotal = BigDecimal.ZERO;
        
        for (DetalleVenta detalle : productosVenta) {
            this.subtotal = this.subtotal.add(detalle.getSubtotal());
        }
        
        this.impuestos = this.subtotal.multiply(TASA_IMPUESTO);
        this.total = this.subtotal.add(this.impuestos);
        
        return true;
    }
    
    /**
     * Finaliza la venta cambiando su estado a COMPLETADA.
     * 
     * @return true si se finalizó correctamente, false en caso contrario
     */
    public boolean finalizarVenta() {
        if (estado == EstadoVenta.PROCESANDO && !productosVenta.isEmpty()) {
            this.estado = EstadoVenta.COMPLETADA;
            calcularTotal();
            return true;
        }
        return false;
    }
    
    /**
     * Cancela la venta cambiando su estado a CANCELADA y devolviendo el stock.
     * 
     * @return true si se canceló correctamente, false en caso contrario
     */
    public boolean cancelarVenta() {
        if (estado == EstadoVenta.PROCESANDO) {
            // Devolver stock de todos los productos
            for (DetalleVenta detalle : productosVenta) {
                if (detalle.getProducto() != null) {
                    detalle.getProducto().actualizarStock(detalle.getCantidad());
                }
            }
            
            this.estado = EstadoVenta.CANCELADA;
            return true;
        }
        return false;
    }
}

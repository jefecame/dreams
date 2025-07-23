package com.jefecame.dreams.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Venta {
    private int id;
    private UUID idCliente;
    private List<ProductoVenta> productosVenta;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private String estado;

    public Venta(UUID idCliente, LocalDateTime fecha) {
        this.idCliente = idCliente;
        this.fechaCreacion = fecha;
        this.productosVenta = new ArrayList<>();
        this.subtotal = BigDecimal.ZERO;
        this.impuestos = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
        this.estado = "PROCESANDO";
    }

    public int getId() { return id; }
    public UUID getIdCliente() { return idCliente; }
    public List<ProductoVenta> getProductosVenta() { return productosVenta; }
    public BigDecimal getSubtotal() { return subtotal; }
    public BigDecimal getImpuestos() { return impuestos; }
    public BigDecimal getTotal() { return total; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getEstado() { return estado; }

    public boolean agregarProducto(Producto producto, int cantidad) {
        if (!producto.validarDisponibilidad(cantidad)) return false;
        ProductoVenta pv = new ProductoVenta(producto.getId(), this.id, cantidad, producto.getPrecio());
        productosVenta.add(pv);
        producto.actualizarStock(-cantidad);
        calcularTotal();
        return true;
    }

    public boolean removerProducto(int idProductoVenta) {
        return productosVenta.removeIf(pv -> pv.getId() == idProductoVenta);
    }

    public boolean calcularTotal() {
        subtotal = productosVenta.stream()
                .map(pv -> BigDecimal.valueOf(pv.getSubtotal()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        impuestos = subtotal.multiply(BigDecimal.valueOf(0.16)); // Ejemplo 16% IVA
        total = subtotal.add(impuestos);
        return true;
    }

    public boolean finalizarVenta() {
        this.estado = "COMPLETADA";
        return true;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", productosVenta=" + productosVenta +
                ", subtotal=" + subtotal +
                ", impuestos=" + impuestos +
                ", total=" + total +
                ", fechaCreacion=" + fechaCreacion +
                ", estado='" + estado + '\'' +
                '}';
    }
    
}
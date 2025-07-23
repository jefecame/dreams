package com.jefecame.dreams.model;

public class ProductoVenta {
    private int id;
    private int idProducto;
    private int idVenta;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ProductoVenta(int idProducto, int idVenta, int cantidad, double precioUnitario) {
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = calcularSubtotal();
    }

    public int getId() { return id; }
    public int getIdProducto() { return idProducto; }
    public int getIdVenta() { return idVenta; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double getSubtotal() { return subtotal; }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = calcularSubtotal();
    }

    public double calcularSubtotal() {
        return this.cantidad * this.precioUnitario;
    }
}
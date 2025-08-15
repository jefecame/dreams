package com.jefecame.dreams.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Venta - Representa una venta en el sistema.
 * Gestiona los productos vendidos y calcula los totales correspondientes.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Venta {
    
    private static int contadorId = 0;
    private static final BigDecimal TASA_IMPUESTO_DEFAULT = new BigDecimal("0.16"); // 16% IVA
    
    private final int id;
    private UUID idCliente;
    private List<ProductoVenta> productosVenta;
    private BigDecimal subtotal;
    private BigDecimal impuestos;
    private BigDecimal total;
    private final LocalDateTime fechaCreacion;
    private LocalDateTime fechaUltimaActualizacion;
    private EstadoVenta estado;
    private BigDecimal tasaImpuesto;
    private String comentarios;

    /**
     * Constructor para crear una nueva venta.
     * 
     * @param idCliente el UUID del cliente
     * @throws IllegalArgumentException si el idCliente es null
     */
    public Venta(UUID idCliente) {
        this(idCliente, LocalDateTime.now());
    }
    
    /**
     * Constructor para crear una nueva venta con fecha específica.
     * 
     * @param idCliente el UUID del cliente
     * @param fecha la fecha de creación de la venta
     * @throws IllegalArgumentException si algún parámetro es null
     */
    public Venta(UUID idCliente, LocalDateTime fecha) {
        validarIdCliente(idCliente);
        validarFecha(fecha);
        
        this.id = ++contadorId;
        this.idCliente = idCliente;
        this.fechaCreacion = fecha;
        this.fechaUltimaActualizacion = fecha;
        this.productosVenta = new ArrayList<>();
        this.subtotal = BigDecimal.ZERO;
        this.impuestos = BigDecimal.ZERO;
        this.total = BigDecimal.ZERO;
        this.estado = EstadoVenta.PROCESANDO;
        this.tasaImpuesto = TASA_IMPUESTO_DEFAULT;
        this.comentarios = "";
    }

    // Getters
    
    /**
     * Obtiene el ID único de la venta.
     * 
     * @return el ID de la venta
     */
    public int getId() { 
        return id; 
    }
    
    /**
     * Obtiene el UUID del cliente.
     * 
     * @return el UUID del cliente
     */
    public UUID getIdCliente() { 
        return idCliente; 
    }
    
    /**
     * Obtiene una copia de solo lectura de la lista de productos en la venta.
     * 
     * @return lista inmutable de productos en la venta
     */
    public List<ProductoVenta> getProductosVenta() { 
        return Collections.unmodifiableList(productosVenta); 
    }
    
    /**
     * Obtiene el subtotal de la venta (sin impuestos).
     * 
     * @return el subtotal
     */
    public BigDecimal getSubtotal() { 
        return subtotal; 
    }
    
    /**
     * Obtiene los impuestos de la venta.
     * 
     * @return los impuestos
     */
    public BigDecimal getImpuestos() { 
        return impuestos; 
    }
    
    /**
     * Obtiene el total de la venta (subtotal + impuestos).
     * 
     * @return el total
     */
    public BigDecimal getTotal() { 
        return total; 
    }
    
    /**
     * Obtiene la fecha de creación de la venta.
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
    
    /**
     * Obtiene el estado actual de la venta.
     * 
     * @return el estado de la venta
     */
    public EstadoVenta getEstado() { 
        return estado; 
    }
    
    /**
     * Obtiene la tasa de impuesto aplicada.
     * 
     * @return la tasa de impuesto
     */
    public BigDecimal getTasaImpuesto() {
        return tasaImpuesto;
    }
    
    /**
     * Obtiene los comentarios de la venta.
     * 
     * @return los comentarios
     */
    public String getComentarios() {
        return comentarios;
    }
    
    // Setters con validación
    
    /**
     * Establece la tasa de impuesto para la venta.
     * 
     * @param tasaImpuesto la nueva tasa de impuesto (debe ser no negativa)
     * @throws IllegalArgumentException si la tasa no es válida
     */
    public void setTasaImpuesto(BigDecimal tasaImpuesto) {
        if (tasaImpuesto == null || tasaImpuesto.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La tasa de impuesto no puede ser null o negativa");
        }
        this.tasaImpuesto = tasaImpuesto;
        calcularTotal(); // Recalcular con la nueva tasa
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }
    
    /**
     * Establece comentarios para la venta.
     * 
     * @param comentarios los comentarios (puede ser null)
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios != null ? comentarios.trim() : "";
        this.fechaUltimaActualizacion = LocalDateTime.now();
    }

    // Métodos de negocio
    
    /**
     * Agrega un producto a la venta.
     * 
     * @param producto el producto a agregar
     * @param cantidad la cantidad del producto
     * @return true si el producto fue agregado exitosamente, false en caso contrario
     * @throws IllegalArgumentException si los parámetros no son válidos
     * @throws IllegalStateException si la venta ya fue completada
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        validarVentaModificable();
        
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser null");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (!producto.validarDisponibilidad(cantidad)) {
            return false;
        }
        
        // Verificar si el producto ya está en la venta
        for (ProductoVenta pv : productosVenta) {
            if (pv.getIdProducto() == producto.getId()) {
                // Actualizar cantidad existente
                int nuevaCantidad = pv.getCantidad() + cantidad;
                if (!producto.validarDisponibilidad(nuevaCantidad - pv.getCantidad())) {
                    return false;
                }
                pv.setCantidad(nuevaCantidad);
                producto.actualizarStock(-cantidad);
                calcularTotal();
                return true;
            }
        }
        
        // Agregar nuevo producto
        ProductoVenta pv = new ProductoVenta(producto.getId(), this.id, cantidad, producto.getPrecio());
        productosVenta.add(pv);
        producto.actualizarStock(-cantidad);
        calcularTotal();
        this.fechaUltimaActualizacion = LocalDateTime.now();
        
        return true;
    }

    /**
     * Remueve un producto de la venta.
     * 
     * @param idProductoVenta el ID del ProductoVenta a remover
     * @return true si el producto fue removido, false si no se encontró
     * @throws IllegalStateException si la venta ya fue completada
     */
    public boolean removerProducto(int idProductoVenta) {
        validarVentaModificable();
        
        boolean removido = productosVenta.removeIf(pv -> pv.getId() == idProductoVenta);
        if (removido) {
            calcularTotal();
            this.fechaUltimaActualizacion = LocalDateTime.now();
        }
        return removido;
    }
    
    /**
     * Actualiza la cantidad de un producto en la venta.
     * 
     * @param idProductoVenta el ID del ProductoVenta
     * @param nuevaCantidad la nueva cantidad
     * @return true si se actualizó exitosamente, false en caso contrario
     * @throws IllegalStateException si la venta ya fue completada
     */
    public boolean actualizarCantidadProducto(int idProductoVenta, int nuevaCantidad) {
        validarVentaModificable();
        
        if (nuevaCantidad <= 0) {
            return removerProducto(idProductoVenta);
        }
        
        for (ProductoVenta pv : productosVenta) {
            if (pv.getId() == idProductoVenta) {
                pv.setCantidad(nuevaCantidad);
                calcularTotal();
                this.fechaUltimaActualizacion = LocalDateTime.now();
                return true;
            }
        }
        return false;
    }

    /**
     * Calcula el subtotal, impuestos y total de la venta.
     * 
     * @return true siempre (para compatibilidad)
     */
    public boolean calcularTotal() {
        subtotal = productosVenta.stream()
                .map(ProductoVenta::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
                
        impuestos = subtotal.multiply(tasaImpuesto)
                           .setScale(2, RoundingMode.HALF_UP);
                           
        total = subtotal.add(impuestos)
                       .setScale(2, RoundingMode.HALF_UP);
                       
        return true;
    }

    /**
     * Finaliza la venta cambiando su estado a COMPLETADA.
     * 
     * @return true si se finalizó exitosamente, false si ya estaba finalizada
     * @throws IllegalStateException si la venta no tiene productos
     */
    public boolean finalizarVenta() {
        if (estado == EstadoVenta.COMPLETADA) {
            return false;
        }
        
        if (productosVenta.isEmpty()) {
            throw new IllegalStateException("No se puede finalizar una venta sin productos");
        }
        
        this.estado = EstadoVenta.COMPLETADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
        return true;
    }
    
    /**
     * Cancela la venta cambiando su estado a CANCELADA.
     * 
     * @return true si se canceló exitosamente, false si ya estaba cancelada o completada
     */
    public boolean cancelarVenta() {
        if (estado == EstadoVenta.CANCELADA || estado == EstadoVenta.COMPLETADA) {
            return false;
        }
        
        this.estado = EstadoVenta.CANCELADA;
        this.fechaUltimaActualizacion = LocalDateTime.now();
        return true;
    }
    
    /**
     * Verifica si la venta está vacía (sin productos).
     * 
     * @return true si la venta no tiene productos, false en caso contrario
     */
    public boolean estaVacia() {
        return productosVenta.isEmpty();
    }
    
    /**
     * Obtiene el número total de productos en la venta.
     * 
     * @return el número de productos diferentes
     */
    public int getCantidadProductos() {
        return productosVenta.size();
    }
    
    /**
     * Obtiene la cantidad total de unidades en la venta.
     * 
     * @return la suma de todas las cantidades
     */
    public int getCantidadTotalUnidades() {
        return productosVenta.stream()
                .mapToInt(ProductoVenta::getCantidad)
                .sum();
    }
    
    // Métodos de validación privados
    
    private void validarIdCliente(UUID idCliente) {
        if (idCliente == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser null");
        }
    }
    
    private void validarFecha(LocalDateTime fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser null");
        }
    }
    
    private void validarVentaModificable() {
        if (estado == EstadoVenta.COMPLETADA || estado == EstadoVenta.CANCELADA) {
            throw new IllegalStateException("No se puede modificar una venta que está " + estado.getNombre());
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Venta venta = (Venta) obj;
        return id == venta.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format(
            "Venta{id=%d, idCliente=%s, productos=%d, subtotal=%s, impuestos=%s, total=%s, estado=%s, fecha=%s}",
            id, idCliente, productosVenta.size(), subtotal, impuestos, total, estado, fechaCreacion
        );
    }
}

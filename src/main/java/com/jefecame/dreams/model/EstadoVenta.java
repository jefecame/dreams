package com.jefecame.dreams.model;

/**
 * Enumeración que define los posibles estados de una venta en el sistema.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public enum EstadoVenta {
    
    /**
     * Estado inicial cuando se crea una venta y se están agregando productos.
     */
    PROCESANDO("Procesando", "La venta se está procesando"),
    
    /**
     * Estado cuando la venta ha sido completada exitosamente.
     */
    COMPLETADA("Completada", "La venta ha sido completada"),
    
    /**
     * Estado cuando la venta ha sido cancelada.
     */
    CANCELADA("Cancelada", "La venta ha sido cancelada"),
    
    /**
     * Estado cuando la venta está en proceso de reembolso.
     */
    REEMBOLSO("Reembolso", "La venta está en proceso de reembolso");
    
    private final String nombre;
    private final String descripcion;
    
    /**
     * Constructor del enum EstadoVenta.
     * 
     * @param nombre el nombre del estado
     * @param descripcion la descripción del estado
     */
    EstadoVenta(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene el nombre del estado.
     * 
     * @return el nombre del estado
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la descripción del estado.
     * 
     * @return la descripción del estado
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Convierte un string al enum correspondiente.
     * 
     * @param estado el string del estado
     * @return el enum correspondiente o null si no existe
     */
    public static EstadoVenta fromString(String estado) {
        if (estado == null) return null;
        
        for (EstadoVenta e : EstadoVenta.values()) {
            if (e.nombre.equalsIgnoreCase(estado) || e.name().equalsIgnoreCase(estado)) {
                return e;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}

package com.jefecame.dreams.model;

/**
 * Enumeración que define los estados posibles de una venta.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public enum EstadoVenta {
    
    /** La venta está siendo procesada */
    PROCESANDO,
    
    /** La venta ha sido completada exitosamente */
    COMPLETADA,
    
    /** La venta ha sido cancelada */
    CANCELADA
}

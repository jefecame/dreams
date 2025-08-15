package com.jefecame.dreams.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the EstadoVenta enum.
 * Tests enum values, methods, and conversion functionality.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("EstadoVenta Enum Tests")
class EstadoVentaTest {
    
    @Test
    @DisplayName("Should have all expected enum values")
    void testEnumValues() {
        EstadoVenta[] estados = EstadoVenta.values();
        
        assertEquals(4, estados.length);
        assertTrue(contains(estados, EstadoVenta.PROCESANDO));
        assertTrue(contains(estados, EstadoVenta.COMPLETADA));
        assertTrue(contains(estados, EstadoVenta.CANCELADA));
        assertTrue(contains(estados, EstadoVenta.REEMBOLSO));
    }
    
    @Test
    @DisplayName("Should have correct names for all states")
    void testGetNombre() {
        assertEquals("Procesando", EstadoVenta.PROCESANDO.getNombre());
        assertEquals("Completada", EstadoVenta.COMPLETADA.getNombre());
        assertEquals("Cancelada", EstadoVenta.CANCELADA.getNombre());
        assertEquals("Reembolso", EstadoVenta.REEMBOLSO.getNombre());
    }
    
    @Test
    @DisplayName("Should have descriptions for all states")
    void testGetDescripcion() {
        assertNotNull(EstadoVenta.PROCESANDO.getDescripcion());
        assertNotNull(EstadoVenta.COMPLETADA.getDescripcion());
        assertNotNull(EstadoVenta.CANCELADA.getDescripcion());
        assertNotNull(EstadoVenta.REEMBOLSO.getDescripcion());
        
        assertTrue(EstadoVenta.PROCESANDO.getDescripcion().length() > 0);
        assertTrue(EstadoVenta.COMPLETADA.getDescripcion().contains("completada"));
        assertTrue(EstadoVenta.CANCELADA.getDescripcion().contains("cancelada"));
        assertTrue(EstadoVenta.REEMBOLSO.getDescripcion().contains("reembolso"));
    }
    
    @Test
    @DisplayName("Should convert from string by name")
    void testFromStringByName() {
        assertEquals(EstadoVenta.PROCESANDO, EstadoVenta.fromString("Procesando"));
        assertEquals(EstadoVenta.COMPLETADA, EstadoVenta.fromString("Completada"));
        assertEquals(EstadoVenta.CANCELADA, EstadoVenta.fromString("Cancelada"));
        assertEquals(EstadoVenta.REEMBOLSO, EstadoVenta.fromString("Reembolso"));
    }
    
    @Test
    @DisplayName("Should convert from string by enum constant")
    void testFromStringByEnumConstant() {
        assertEquals(EstadoVenta.PROCESANDO, EstadoVenta.fromString("PROCESANDO"));
        assertEquals(EstadoVenta.COMPLETADA, EstadoVenta.fromString("COMPLETADA"));
        assertEquals(EstadoVenta.CANCELADA, EstadoVenta.fromString("CANCELADA"));
        assertEquals(EstadoVenta.REEMBOLSO, EstadoVenta.fromString("REEMBOLSO"));
    }
    
    @Test
    @DisplayName("Should be case insensitive")
    void testCaseInsensitiveConversion() {
        assertEquals(EstadoVenta.PROCESANDO, EstadoVenta.fromString("procesando"));
        assertEquals(EstadoVenta.COMPLETADA, EstadoVenta.fromString("COMPLETADA"));
        assertEquals(EstadoVenta.CANCELADA, EstadoVenta.fromString("cancelada"));
        assertEquals(EstadoVenta.REEMBOLSO, EstadoVenta.fromString("ReEmBoLsO"));
    }
    
    @Test
    @DisplayName("Should return null for invalid strings")
    void testInvalidStringConversion() {
        assertNull(EstadoVenta.fromString("INVALID"));
        assertNull(EstadoVenta.fromString(""));
        assertNull(EstadoVenta.fromString("pendiente"));
        assertNull(EstadoVenta.fromString(null));
    }
    
    @Test
    @DisplayName("Should have meaningful toString")
    void testToString() {
        assertEquals("Procesando", EstadoVenta.PROCESANDO.toString());
        assertEquals("Completada", EstadoVenta.COMPLETADA.toString());
        assertEquals("Cancelada", EstadoVenta.CANCELADA.toString());
        assertEquals("Reembolso", EstadoVenta.REEMBOLSO.toString());
    }
    
    @Test
    @DisplayName("Should be usable in switch statements")
    void testSwitchUsage() {
        String result = switch (EstadoVenta.PROCESANDO) {
            case PROCESANDO -> "En proceso";
            case COMPLETADA -> "Finalizada";
            case CANCELADA -> "Cancelada";
            case REEMBOLSO -> "En reembolso";
        };
        
        assertEquals("En proceso", result);
    }
    
    private boolean contains(EstadoVenta[] array, EstadoVenta value) {
        for (EstadoVenta estado : array) {
            if (estado == value) {
                return true;
            }
        }
        return false;
    }
}

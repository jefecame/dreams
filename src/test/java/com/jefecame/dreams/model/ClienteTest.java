package com.jefecame.dreams.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

/**
 * Unit tests for the Cliente class.
 * Tests validation, business logic, and edge cases.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("Cliente Class Tests")
class ClienteTest {
    
    private Cliente cliente;
    
    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan Pérez", "juan.perez@example.com");
    }
    
    @Test
    @DisplayName("Should create cliente with valid data")
    void testValidClienteCreation() {
        assertNotNull(cliente);
        assertNotNull(cliente.getId());
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals("juan.perez@example.com", cliente.getEmail());
        assertTrue(cliente.isActivo());
        assertNotNull(cliente.getFechaCreacion());
        assertNotNull(cliente.getFechaUltimaActualizacion());
    }
    
    @Test
    @DisplayName("Should normalize email to lowercase")
    void testEmailNormalization() {
        Cliente cliente = new Cliente("Ana García", "ANA.GARCIA@EXAMPLE.COM");
        assertEquals("ana.garcia@example.com", cliente.getEmail());
    }
    
    @Test
    @DisplayName("Should trim whitespace from name and email")
    void testWhitespaceTriming() {
        Cliente cliente = new Cliente("  Pedro López  ", "  pedro@example.com  ");
        assertEquals("Pedro López", cliente.getNombre());
        assertEquals("pedro@example.com", cliente.getEmail());
    }
    
    @Test
    @DisplayName("Should throw exception for null nombre")
    void testNullNombreValidation() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cliente(null, "test@example.com")
        );
        assertTrue(exception.getMessage().contains("nombre"));
    }
    
    @Test
    @DisplayName("Should throw exception for empty nombre")
    void testEmptyNombreValidation() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cliente("", "test@example.com")
        );
        assertTrue(exception.getMessage().contains("nombre"));
    }
    
    @Test
    @DisplayName("Should throw exception for short nombre")
    void testShortNombreValidation() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cliente("A", "test@example.com")
        );
        assertTrue(exception.getMessage().contains("al menos 2 caracteres"));
    }
    
    @Test
    @DisplayName("Should throw exception for very long nombre")
    void testLongNombreValidation() {
        String longName = "A".repeat(101);
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cliente(longName, "test@example.com")
        );
        assertTrue(exception.getMessage().contains("100 caracteres"));
    }
    
    @Test
    @DisplayName("Should throw exception for null email")
    void testNullEmailValidation() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new Cliente("Test User", null)
        );
        assertTrue(exception.getMessage().contains("email"));
    }
    
    @Test
    @DisplayName("Should throw exception for invalid email format")
    void testInvalidEmailValidation() {
        String[] invalidEmails = {
            "invalid-email",
            "@example.com",
            "user@",
            "user@.com",
            "user@example", // Missing TLD
            "" // Empty string (handled by empty validation, not format)
        };
        
        for (String invalidEmail : invalidEmails) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Cliente("Test User", invalidEmail),
                "Should reject email: " + invalidEmail
            );
            // Check if it's either format error or empty error
            assertTrue(
                exception.getMessage().contains("formato") || 
                exception.getMessage().contains("vacío"),
                "Expected format or empty error for email: " + invalidEmail + ", but got: " + exception.getMessage()
            );
        }
    }
    
    @Test
    @DisplayName("Should accept valid email formats")
    void testValidEmailFormats() {
        String[] validEmails = {
            "user@example.com",
            "user.name@example.com",
            "user+tag@example.com",
            "user123@example-site.com",
            "test@subdomain.example.org"
        };
        
        for (String validEmail : validEmails) {
            assertDoesNotThrow(
                () -> new Cliente("Test User", validEmail),
                "Should accept email: " + validEmail
            );
        }
    }
    
    @Test
    @DisplayName("Should update nombre correctly")
    void testSetNombre() {
        LocalDateTime beforeUpdate = cliente.getFechaUltimaActualizacion();
        
        // Small delay to ensure time difference
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // ignore
        }
        
        cliente.setNombre("Carlos Ruiz");
        
        assertEquals("Carlos Ruiz", cliente.getNombre());
        assertTrue(cliente.getFechaUltimaActualizacion().isAfter(beforeUpdate));
    }
    
    @Test
    @DisplayName("Should update email correctly")
    void testSetEmail() {
        LocalDateTime beforeUpdate = cliente.getFechaUltimaActualizacion();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // ignore
        }
        
        cliente.setEmail("CARLOS.RUIZ@EXAMPLE.COM");
        
        assertEquals("carlos.ruiz@example.com", cliente.getEmail());
        assertTrue(cliente.getFechaUltimaActualizacion().isAfter(beforeUpdate));
    }
    
    @Test
    @DisplayName("Should update active status correctly")
    void testSetActivo() {
        LocalDateTime beforeUpdate = cliente.getFechaUltimaActualizacion();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // ignore
        }
        
        cliente.setActivo(false);
        
        assertFalse(cliente.isActivo());
        assertTrue(cliente.getFechaUltimaActualizacion().isAfter(beforeUpdate));
    }
    
    @Test
    @DisplayName("Should update all information transactionally")
    void testActualizarInformacion() {
        LocalDateTime beforeUpdate = cliente.getFechaUltimaActualizacion();
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // ignore
        }
        
        cliente.actualizarInformacion("María González", "maria@example.com", false);
        
        assertEquals("María González", cliente.getNombre());
        assertEquals("maria@example.com", cliente.getEmail());
        assertFalse(cliente.isActivo());
        assertTrue(cliente.getFechaUltimaActualizacion().isAfter(beforeUpdate));
    }
    
    @Test
    @DisplayName("Should handle equals and hashCode correctly")
    void testEqualsAndHashCode() {
        Cliente cliente1 = new Cliente("Test User", "test@example.com");
        Cliente cliente2 = new Cliente("Another User", "another@example.com");
        Cliente cliente3 = cliente1;
        
        // Test reflexive
        assertEquals(cliente1, cliente1);
        
        // Test symmetric
        assertNotEquals(cliente1, cliente2);
        assertNotEquals(cliente2, cliente1);
        
        // Test transitive and consistent
        assertEquals(cliente1, cliente3);
        assertEquals(cliente3, cliente1);
        
        // Test null
        assertNotEquals(cliente1, null);
        
        // Test different class
        assertNotEquals(cliente1, "Not a Cliente");
        
        // Test hashCode consistency
        assertEquals(cliente1.hashCode(), cliente1.hashCode());
        assertEquals(cliente1.hashCode(), cliente3.hashCode());
    }
    
    @Test
    @DisplayName("Should have meaningful toString")
    void testToString() {
        String toString = cliente.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("Cliente"));
        assertTrue(toString.contains(cliente.getId().toString()));
        assertTrue(toString.contains("Juan Pérez"));
        assertTrue(toString.contains("juan.perez@example.com"));
        assertTrue(toString.contains("true")); // activo
    }
}

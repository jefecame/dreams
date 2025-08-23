package com.jefecame.dreams.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Comprehensive unit tests for the Cliente class.
 * Tests all aspects of client management functionality.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("Cliente Model Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ClienteTest {
    
    private Cliente cliente;
    
    @BeforeEach
    void setUp() {
        cliente = new Cliente(1, "Juan Pérez", "juan.perez@email.com");
    }
    
    @Test
    @Order(1)
    @DisplayName("Cliente should be created with valid parameters")
    void testClienteCreation() {
        assertNotNull(cliente, "Cliente should not be null");
        assertEquals(1, cliente.getId(), "Cliente ID should match");
        assertEquals("Juan Pérez", cliente.getNombre(), "Cliente name should match");
        assertEquals("juan.perez@email.com", cliente.getEmail(), "Cliente email should match");
        assertTrue(cliente.isActivo(), "New cliente should be active by default");
    }
    
    @Test
    @Order(2)
    @DisplayName("Cliente getters should return correct values")
    void testClienteGetters() {
        assertEquals(1, cliente.getId(), "getId should return correct ID");
        assertEquals("Juan Pérez", cliente.getNombre(), "getNombre should return correct name");
        assertEquals("juan.perez@email.com", cliente.getEmail(), "getEmail should return correct email");
        assertTrue(cliente.isActivo(), "isActivo should return true for new client");
    }
    
    @Test
    @Order(3)
    @DisplayName("Cliente setters should update values correctly")
    void testClienteSetters() {
        // Test setNombre
        cliente.setNombre("María García");
        assertEquals("María García", cliente.getNombre(), "setNombre should update name");
        
        // Test setEmail
        cliente.setEmail("maria.garcia@email.com");
        assertEquals("maria.garcia@email.com", cliente.getEmail(), "setEmail should update email");
        
        // Test setActivo
        cliente.setActivo(false);
        assertFalse(cliente.isActivo(), "setActivo should update active status");
    }
    
    @Test
    @Order(4)
    @DisplayName("actualizarInformacion should update all fields")
    void testActualizarInformacion() {
        cliente.actualizarInformacion("Pedro López", "pedro.lopez@email.com", false);
        
        assertEquals("Pedro López", cliente.getNombre(), "Name should be updated");
        assertEquals("pedro.lopez@email.com", cliente.getEmail(), "Email should be updated");
        assertFalse(cliente.isActivo(), "Active status should be updated");
    }
    
    @Test
    @Order(5)
    @DisplayName("Cliente should handle edge cases properly")
    void testClienteEdgeCases() {
        // Test with empty strings
        cliente.setNombre("");
        assertEquals("", cliente.getNombre(), "Should handle empty name");
        
        cliente.setEmail("");
        assertEquals("", cliente.getEmail(), "Should handle empty email");
        
        // Test with null values (if allowed by implementation)
        cliente.setNombre(null);
        assertNull(cliente.getNombre(), "Should handle null name");
        
        cliente.setEmail(null);
        assertNull(cliente.getEmail(), "Should handle null email");
    }
    
    @Test
    @Order(6)
    @DisplayName("Cliente should handle special characters in name and email")
    void testSpecialCharacters() {
        String nameWithSpecialChars = "José María Ñoño-García";
        String emailWithSpecialChars = "jose.maria@test-domain.co.mx";
        
        cliente.setNombre(nameWithSpecialChars);
        cliente.setEmail(emailWithSpecialChars);
        
        assertEquals(nameWithSpecialChars, cliente.getNombre(), "Should handle special characters in name");
        assertEquals(emailWithSpecialChars, cliente.getEmail(), "Should handle special characters in email");
    }
    
    @Test
    @Order(7)
    @DisplayName("Cliente should maintain data integrity")
    void testDataIntegrity() {
        int originalId = cliente.getId();
        
        // ID should be immutable (no setter)
        assertEquals(originalId, cliente.getId(), "ID should remain constant");
        
        // Test multiple updates
        cliente.actualizarInformacion("Nombre1", "email1@test.com", true);
        cliente.actualizarInformacion("Nombre2", "email2@test.com", false);
        
        assertEquals("Nombre2", cliente.getNombre(), "Should have latest name");
        assertEquals("email2@test.com", cliente.getEmail(), "Should have latest email");
        assertFalse(cliente.isActivo(), "Should have latest active status");
    }
    
    @Test
    @Order(8)
    @DisplayName("Cliente with different IDs should be different")
    void testClienteWithDifferentIds() {
        Cliente otherCliente = new Cliente(2, "Juan Pérez", "juan.perez@email.com");
        
        assertNotEquals(cliente.getId(), otherCliente.getId(), "Different clients should have different IDs");
        assertEquals(cliente.getNombre(), otherCliente.getNombre(), "Names can be the same");
        assertEquals(cliente.getEmail(), otherCliente.getEmail(), "Emails can be the same");
    }
    
    @Test
    @Order(9)
    @DisplayName("Cliente state changes should be consistent")
    void testStateConsistency() {
        // Initially active
        assertTrue(cliente.isActivo(), "Should be active initially");
        
        // Deactivate
        cliente.setActivo(false);
        assertFalse(cliente.isActivo(), "Should be inactive after deactivation");
        
        // Reactivate
        cliente.setActivo(true);
        assertTrue(cliente.isActivo(), "Should be active after reactivation");
        
        // Test with actualizarInformacion
        cliente.actualizarInformacion("Test Name", "test@email.com", false);
        assertFalse(cliente.isActivo(), "Should respect active status in actualizarInformacion");
    }
    
    @Test
    @Order(10)
    @DisplayName("Cliente should handle long strings")
    void testLongStrings() {
        String longName = "A".repeat(1000);
        String longEmail = "a".repeat(50) + "@" + "b".repeat(50) + ".com";
        
        cliente.setNombre(longName);
        cliente.setEmail(longEmail);
        
        assertEquals(longName, cliente.getNombre(), "Should handle long names");
        assertEquals(longEmail, cliente.getEmail(), "Should handle long emails");
    }
}

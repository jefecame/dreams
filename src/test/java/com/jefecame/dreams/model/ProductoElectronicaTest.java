package com.jefecame.dreams.model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

/**
 * Comprehensive unit tests for the ProductoElectronica class.
 * Tests all aspects of electronic product functionality.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("ProductoElectronica Model Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductoElectronicaTest {
    
    private ProductoElectronica producto;
    private final BigDecimal precioTest = new BigDecimal("299.99");
    
    @BeforeEach
    void setUp() {
        producto = new ProductoElectronica("Smartphone", precioTest, 10, "Samsung", "Galaxy S21");
    }
    
    @Test
    @Order(1)
    @DisplayName("ProductoElectronica should be created with valid parameters")
    void testProductoElectronicaCreation() {
        assertNotNull(producto, "Producto should not be null");
        assertEquals("Smartphone", producto.getNombre(), "Nombre should match");
        assertEquals(precioTest, producto.getPrecio(), "Precio should match");
        assertEquals(10, producto.getStock(), "Stock should match");
        assertEquals("Samsung", producto.getMarca(), "Marca should match");
        assertEquals("Galaxy S21", producto.getModelo(), "Modelo should match");
        assertTrue(producto.isActivo(), "New product should be active by default");
        assertTrue(producto.getId() > 0, "Product should have a valid ID");
    }
    
    @Test
    @Order(2)
    @DisplayName("ProductoElectronica getters should return correct values")
    void testGetters() {
        assertEquals("Smartphone", producto.getNombre(), "getNombre should work");
        assertEquals(precioTest, producto.getPrecio(), "getPrecio should work");
        assertEquals(10, producto.getStock(), "getStock should work");
        assertEquals("Samsung", producto.getMarca(), "getMarca should work");
        assertEquals("Galaxy S21", producto.getModelo(), "getModelo should work");
        assertTrue(producto.isActivo(), "isActivo should work");
    }
    
    @Test
    @Order(3)
    @DisplayName("ProductoElectronica setters should update values correctly")
    void testSetters() {
        // Test inherited setters
        producto.setNombre("Laptop");
        assertEquals("Laptop", producto.getNombre(), "setNombre should update name");
        
        producto.setPrecio(new BigDecimal("599.99"));
        assertEquals(new BigDecimal("599.99"), producto.getPrecio(), "setPrecio should update price");
        
        producto.setStock(15);
        assertEquals(15, producto.getStock(), "setStock should update stock");
        
        producto.setActivo(false);
        assertFalse(producto.isActivo(), "setActivo should update active status");
        
        // Test specific setters
        producto.setMarca("Apple");
        assertEquals("Apple", producto.getMarca(), "setMarca should update marca");
        
        producto.setModelo("MacBook Pro");
        assertEquals("MacBook Pro", producto.getModelo(), "setModelo should update modelo");
    }
    
    @Test
    @Order(4)
    @DisplayName("mostrarDetalles should return formatted string")
    void testMostrarDetalles() {
        String detalles = producto.mostrarDetalles();
        
        assertNotNull(detalles, "Detalles should not be null");
        assertTrue(detalles.contains("Smartphone"), "Should contain product name");
        assertTrue(detalles.contains("Samsung"), "Should contain marca");
        assertTrue(detalles.contains("Galaxy S21"), "Should contain modelo");
        assertTrue(detalles.contains("299.99"), "Should contain price");
        assertTrue(detalles.contains("10"), "Should contain stock");
        assertTrue(detalles.contains("Producto Electrónico"), "Should identify as electronic product");
    }
    
    @Test
    @Order(5)
    @DisplayName("validarDisponibilidad should work correctly")
    void testValidarDisponibilidad() {
        assertTrue(producto.validarDisponibilidad(5), "Should have availability for 5 units");
        assertTrue(producto.validarDisponibilidad(10), "Should have availability for exactly 10 units");
        assertFalse(producto.validarDisponibilidad(15), "Should not have availability for 15 units");
        assertFalse(producto.validarDisponibilidad(0), "Should not validate 0 units");
        
        // Test with inactive product
        producto.setActivo(false);
        assertFalse(producto.validarDisponibilidad(1), "Inactive product should not be available");
    }
    
    @Test
    @Order(6)
    @DisplayName("actualizarStock should work correctly")
    void testActualizarStock() {
        int initialStock = producto.getStock();
        
        // Add stock
        producto.actualizarStock(5);
        assertEquals(initialStock + 5, producto.getStock(), "Stock should increase by 5");
        
        // Remove stock
        producto.actualizarStock(-3);
        assertEquals(initialStock + 2, producto.getStock(), "Stock should decrease by 3");
        
        // Test negative stock prevention
        producto.actualizarStock(-100);
        assertEquals(0, producto.getStock(), "Stock should not go below 0");
    }
    
    @Test
    @Order(7)
    @DisplayName("ProductoElectronica should handle edge cases")
    void testEdgeCases() {
        // Test with null values
        producto.setMarca(null);
        assertNull(producto.getMarca(), "Should handle null marca");
        
        producto.setModelo(null);
        assertNull(producto.getModelo(), "Should handle null modelo");
        
        // Test with empty strings
        producto.setMarca("");
        assertEquals("", producto.getMarca(), "Should handle empty marca");
        
        producto.setModelo("");
        assertEquals("", producto.getModelo(), "Should handle empty modelo");
    }
    
    @Test
    @Order(8)
    @DisplayName("ProductoElectronica should handle special characters")
    void testSpecialCharacters() {
        String marcaWithSpecialChars = "Läptöp-Brañd®";
        String modeloWithSpecialChars = "Modêl-X1™";
        
        producto.setMarca(marcaWithSpecialChars);
        producto.setModelo(modeloWithSpecialChars);
        
        assertEquals(marcaWithSpecialChars, producto.getMarca(), "Should handle special characters in marca");
        assertEquals(modeloWithSpecialChars, producto.getModelo(), "Should handle special characters in modelo");
    }
    
    @Test
    @Order(9)
    @DisplayName("setCategoria should not break functionality")
    void testSetCategoria() {
        // Test the empty setCategoria method from parent class
        assertDoesNotThrow(() -> {
            producto.setCategoria("Electronics");
        }, "setCategoria should not throw exception");
        
        // Verify other functionality still works
        assertEquals("Samsung", producto.getMarca(), "Other functionality should remain intact");
    }
    
    @Test
    @Order(10)
    @DisplayName("ProductoElectronica with different parameters should work")
    void testDifferentParameters() {
        ProductoElectronica tablet = new ProductoElectronica(
            "Tablet", 
            new BigDecimal("199.50"), 
            25, 
            "Apple", 
            "iPad Air"
        );
        
        assertNotNull(tablet, "Tablet should be created");
        assertEquals("Tablet", tablet.getNombre(), "Tablet name should match");
        assertEquals(new BigDecimal("199.50"), tablet.getPrecio(), "Tablet price should match");
        assertEquals(25, tablet.getStock(), "Tablet stock should match");
        assertEquals("Apple", tablet.getMarca(), "Tablet marca should match");
        assertEquals("iPad Air", tablet.getModelo(), "Tablet modelo should match");
        
        // IDs should be different
        assertNotEquals(producto.getId(), tablet.getId(), "Different products should have different IDs");
    }
    
    @Test
    @Order(11)
    @DisplayName("ProductoElectronica should handle large numbers")
    void testLargeNumbers() {
        BigDecimal largePrice = new BigDecimal("99999999.99");
        producto.setPrecio(largePrice);
        assertEquals(largePrice, producto.getPrecio(), "Should handle large prices");
        
        producto.setStock(Integer.MAX_VALUE);
        assertEquals(Integer.MAX_VALUE, producto.getStock(), "Should handle large stock numbers");
    }
    
    @Test
    @Order(12)
    @DisplayName("ProductoElectronica inheritance should work correctly")
    void testInheritance() {
        assertTrue(producto instanceof Producto, "Should be instance of Producto");
        assertTrue(producto instanceof ProductoElectronica, "Should be instance of ProductoElectronica");
        
        // Test polymorphism
        Producto productoBase = new ProductoElectronica("Test", new BigDecimal("100"), 5, "Test", "Model");
        assertNotNull(productoBase.mostrarDetalles(), "Polymorphic call should work");
        assertTrue(productoBase.mostrarDetalles().contains("Producto Electrónico"), "Should show correct type");
    }
}

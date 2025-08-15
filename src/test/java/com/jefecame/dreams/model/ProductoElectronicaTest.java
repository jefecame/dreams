package com.jefecame.dreams.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

/**
 * Unit tests for the ProductoElectronica class.
 * Tests inheritance, validation, and specific electronics functionality.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("ProductoElectronica Class Tests")
class ProductoElectronicaTest {
    
    private ProductoElectronica producto;
    
    @BeforeEach
    void setUp() {
        producto = new ProductoElectronica(
            "iPhone 15 Pro",
            new BigDecimal("1299.99"),
            10,
            "Apple",
            "A2848"
        );
    }
    
    @Test
    @DisplayName("Should create electronics product with valid data")
    void testValidProductCreation() {
        assertNotNull(producto);
        assertEquals("iPhone 15 Pro", producto.getNombre());
        assertEquals(new BigDecimal("1299.99"), producto.getPrecio());
        assertEquals(CategoriaProducto.ELECTRONICA, producto.getCategoria());
        assertEquals(10, producto.getStock());
        assertTrue(producto.isActivo());
        assertEquals("Apple", producto.getMarca());
        assertEquals("A2848", producto.getModelo());
        assertEquals(12, producto.getGarantiaMeses()); // default warranty
        assertNotNull(producto.getFechaCreacion());
    }
    
    @Test
    @DisplayName("Should create electronics product with custom warranty")
    void testProductWithCustomWarranty() {
        ProductoElectronica productoConGarantia = new ProductoElectronica(
            "MacBook Pro",
            new BigDecimal("2499.99"),
            5,
            "Apple",
            "MBP-2023",
            24 // 2 years warranty
        );
        
        assertEquals(24, productoConGarantia.getGarantiaMeses());
    }
    
    @Test
    @DisplayName("Should inherit from Producto correctly")
    void testInheritance() {
        assertTrue(producto instanceof Producto);
        
        // Test inherited methods
        assertEquals("iPhone 15 Pro", producto.getNombre());
        assertTrue(producto.validarDisponibilidad(5));
        assertFalse(producto.validarDisponibilidad(15)); // more than stock
    }
    
    @Test
    @DisplayName("Should validate marca correctly")
    void testMarcaValidation() {
        // Test null marca
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, null, "Model")
        );
        assertTrue(exception.getMessage().contains("marca"));
        
        // Test empty marca
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "", "Model")
        );
        assertTrue(exception.getMessage().contains("marca"));
        
        // Test short marca
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "A", "Model")
        );
        assertTrue(exception.getMessage().contains("al menos 2 caracteres"));
        
        // Test long marca
        String longMarca = "A".repeat(51);
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, longMarca, "Model")
        );
        assertTrue(exception.getMessage().contains("50 caracteres"));
    }
    
    @Test
    @DisplayName("Should validate modelo correctly")
    void testModeloValidation() {
        // Test null modelo
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "Apple", null)
        );
        assertTrue(exception.getMessage().contains("modelo"));
        
        // Test empty modelo
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "Apple", "")
        );
        assertTrue(exception.getMessage().contains("modelo"));
        
        // Test long modelo
        String longModelo = "A".repeat(101);
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "Apple", longModelo)
        );
        assertTrue(exception.getMessage().contains("100 caracteres"));
    }
    
    @Test
    @DisplayName("Should validate garantia correctly")
    void testGarantiaValidation() {
        // Test negative warranty
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "Apple", "Model", -1)
        );
        assertTrue(exception.getMessage().contains("garantía"));
        
        // Test excessive warranty
        exception = assertThrows(
            IllegalArgumentException.class,
            () -> new ProductoElectronica("Test", new BigDecimal("100"), 1, "Apple", "Model", 121)
        );
        assertTrue(exception.getMessage().contains("120 meses"));
    }
    
    @Test
    @DisplayName("Should trim whitespace in marca and modelo")
    void testWhitespaceTriming() {
        ProductoElectronica producto = new ProductoElectronica(
            "Test Product",
            new BigDecimal("100"),
            1,
            "  Apple  ",
            "  iPhone-15  "
        );
        
        assertEquals("Apple", producto.getMarca());
        assertEquals("iPhone-15", producto.getModelo());
    }
    
    @Test
    @DisplayName("Should update marca correctly")
    void testSetMarca() {
        producto.setMarca("  Samsung  ");
        assertEquals("Samsung", producto.getMarca());
        assertNotNull(producto.getFechaUltimaActualizacion());
    }
    
    @Test
    @DisplayName("Should update modelo correctly")
    void testSetModelo() {
        producto.setModelo("  Galaxy-S24  ");
        assertEquals("Galaxy-S24", producto.getModelo());
        assertNotNull(producto.getFechaUltimaActualizacion());
    }
    
    @Test
    @DisplayName("Should update garantia correctly")
    void testSetGarantiaMeses() {
        producto.setGarantiaMeses(36);
        assertEquals(36, producto.getGarantiaMeses());
        assertNotNull(producto.getFechaUltimaActualizacion());
        
        // Test invalid warranty
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> producto.setGarantiaMeses(-1)
        );
        assertTrue(exception.getMessage().contains("garantía"));
    }
    
    @Test
    @DisplayName("Should override mostrarDetalles correctly")
    void testMostrarDetalles() {
        String detalles = producto.mostrarDetalles();
        
        assertNotNull(detalles);
        assertTrue(detalles.contains("Producto Electrónico"));
        assertTrue(detalles.contains("iPhone 15 Pro"));
        assertTrue(detalles.contains("Apple"));
        assertTrue(detalles.contains("A2848"));
        assertTrue(detalles.contains("12 meses"));
        assertTrue(detalles.contains("1299.99"));
        assertTrue(detalles.contains("Activo"));
    }
    
    @Test
    @DisplayName("Should have meaningful toString")
    void testToString() {
        String toString = producto.toString();
        
        assertNotNull(toString);
        assertTrue(toString.contains("ProductoElectronica"));
        assertTrue(toString.contains("iPhone 15 Pro"));
        assertTrue(toString.contains("Apple"));
        assertTrue(toString.contains("A2848"));
        assertTrue(toString.contains("12 meses"));
    }
    
    @Test
    @DisplayName("Should validate availability correctly")
    void testValidarDisponibilidad() {
        assertTrue(producto.validarDisponibilidad(5));
        assertTrue(producto.validarDisponibilidad(10));
        assertFalse(producto.validarDisponibilidad(11));
        assertFalse(producto.validarDisponibilidad(0)); // zero is invalid
        assertFalse(producto.validarDisponibilidad(-1)); // negative is invalid
        
        // Test with inactive product
        producto.setActivo(false);
        assertFalse(producto.validarDisponibilidad(5)); // inactive products are not available
    }
    
    @Test
    @DisplayName("Should update stock correctly")
    void testActualizarStock() {
        producto.actualizarStock(5); // add 5
        assertEquals(15, producto.getStock());
        
        producto.actualizarStock(-3); // remove 3
        assertEquals(12, producto.getStock());
        
        // Test invalid stock reduction
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> producto.actualizarStock(-15) // would make stock negative
        );
        assertTrue(exception.getMessage().contains("stock"));
    }
}

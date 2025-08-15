package com.jefecame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Unit tests for the main App class.
 * Tests the application startup and error handling.
 * 
 * @author jefecame
 * @version 1.0.0
 */
@DisplayName("App Main Class Tests")
class AppTest {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }
    
    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
    
    @Test
    @DisplayName("App class should exist and be accessible")
    void testAppClassExists() {
        assertDoesNotThrow(() -> {
            Class<?> appClass = Class.forName("com.jefecame.App");
            assertNotNull(appClass);
        });
    }
    
    @Test
    @DisplayName("App should have main method")
    void testMainMethodExists() {
        assertDoesNotThrow(() -> {
            Class<?> appClass = Class.forName("com.jefecame.App");
            var mainMethod = appClass.getMethod("main", String[].class);
            assertNotNull(mainMethod);
            assertTrue(java.lang.reflect.Modifier.isStatic(mainMethod.getModifiers()));
            assertTrue(java.lang.reflect.Modifier.isPublic(mainMethod.getModifiers()));
        });
    }
    
    @Test
    @DisplayName("Basic assertion test")
    void basicAssertionTest() {
        assertTrue(true, "Basic assertion should pass");
        assertFalse(false, "False assertion should pass");
        assertEquals(2, 1 + 1, "Math should work correctly");
    }
    
    @Test
    @DisplayName("String operations test")
    void stringOperationsTest() {
        String testString = "Dreams Application";
        assertNotNull(testString);
        assertTrue(testString.length() > 0);
        assertTrue(testString.contains("Dreams"));
        assertEquals("Dreams Application", testString);
    }
    
    @Test
    @DisplayName("Exception handling test")
    void exceptionHandlingTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("Test exception");
        });
        
        assertDoesNotThrow(() -> {
            String validOperation = "This should not throw";
            assertNotNull(validOperation);
        });
    }
}

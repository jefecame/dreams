package com.jefecame;

import com.jefecame.dreams.view.Dreams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase principal de la aplicación Dreams - Sistema de Gestión de Ventas.
 * 
 * Esta aplicación permite gestionar clientes, productos y ventas en un
 * sistema de punto de venta simplificado.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class App {
    
    private static final Logger logger = LoggerFactory.getLogger(App.class);
    
    /**
     * Método principal que inicia la aplicación Dreams.
     * 
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        logger.info("Iniciando aplicación Dreams v1.0.0");
        
        try {
            Dreams dreams = new Dreams();
            dreams.mostrarMenuPrincipal();
            
            logger.info("Aplicación Dreams finalizada correctamente");
            
        } catch (Exception e) {
            logger.error("Error fatal en la aplicación Dreams: {}", e.getMessage(), e);
            System.err.println("Error fatal: " + e.getMessage());
            System.err.println("La aplicación se cerrará. Consulte los logs para más detalles.");
            System.exit(1);
        }
    }
}

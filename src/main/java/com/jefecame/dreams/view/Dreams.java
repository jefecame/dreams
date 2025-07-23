package com.jefecame.dreams.view;

import java.util.Scanner;

/**
 * Clase que representa el menú principal de la aplicación Dreams.
 */
public class Dreams {

    /**
     * Método principal que inicia la aplicación y muestra el menú.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Productos");
            System.out.println("3. Gestión de Ventas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.println("Funcionalidad de gestión de clientes (por implementar)");
                    break;
                case 2:
                    System.out.println("Funcionalidad de gestión de productos (por implementar)");
                    break;
                case 3:
                    System.out.println("Funcionalidad de gestión de ventas (por implementar)");
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
        scanner.close();
    }



    
}
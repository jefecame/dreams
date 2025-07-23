package com.jefecame.dreams.view;

import java.util.Scanner;

/**
 * Clase que representa el menú principal de la aplicación Dreams.
 */
public class Dreams {

    /**
     * Mostrar el menú principal de la aplicación.
     */
    public void mostrarMenuPrincipal() {
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
                    MenuClientes.mostrarMenuClientes(scanner);
                    break;
                case 2:
                    MenuProductos.mostrarMenuProductos(scanner);
                    break;
                case 3:
                    MenuVentas.mostrarMenuVentas(scanner);
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
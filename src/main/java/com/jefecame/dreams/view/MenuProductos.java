package com.jefecame.dreams.view;

import java.util.Scanner;

public class MenuProductos {

    public static void mostrarMenuProductos(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Menú de Gestión de Productos ===");
            System.out.println("1. Agregar Producto");
            System.out.println("2. Consultar Producto");
            System.out.println("3. Modificar Producto");
            System.out.println("4. Eliminar Producto");
            System.out.println("5. Listar Productos");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearProducto(scanner);
                    break;
                case 2:
                    leerProducto(scanner);
                    break;
                case 3:
                    actualizarProducto(scanner);
                    break;
                case 4:
                    eliminarProducto(scanner);
                    break;
                case 5:
                    listarProductos();
                    break;
                case 0:
                    System.out.println("Volviendo al Menú Principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    public static void crearProducto(Scanner scanner) {
        System.out.println("Crear Producto (por implementar)");
    }

    public static void leerProducto(Scanner scanner) {
        System.out.println("Consultar Producto (por implementar)");
    }

    public static void actualizarProducto(Scanner scanner) {
        System.out.println("Modificar Producto (por implementar)");
    }

    public static void eliminarProducto(Scanner scanner) {
        System.out.println("Eliminar Producto (por implementar)");
    }

    public static void listarProductos() {
        System.out.println("Listar Productos (por implementar)");
    }
}
package com.jefecame.dreams.view;

import java.util.Scanner;

public class MenuClientes {

    public static void mostrarMenuClientes(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Menú de Gestión de Clientes ===");
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Consultar Cliente");
            System.out.println("3. Modificar Cliente");
            System.out.println("4. Eliminar Cliente");
            System.out.println("5. Listar Clientes");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearCliente(scanner);
                    break;
                case 2:
                    leerCliente(scanner);
                    break;
                case 3:
                    actualizarCliente(scanner);
                    break;
                case 4:
                    eliminarCliente(scanner);
                    break;
                case 5:
                    listarClientes();
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

    public static void crearCliente(Scanner scanner) {
        System.out.println("Crear Cliente (por implementar)");
    }

    public static void leerCliente(Scanner scanner) {
        System.out.println("Consultar Cliente (por implementar)");
    }

    public static void actualizarCliente(Scanner scanner) {
        System.out.println("Modificar Cliente (por implementar)");
    }

    public static void eliminarCliente(Scanner scanner) {
        System.out.println("Eliminar Cliente (por implementar)");
    }

    public static void listarClientes() {
        System.out.println("Listar Clientes (por implementar)");
    }
}
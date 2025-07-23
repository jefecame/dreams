package com.jefecame.dreams.view;

import java.util.Scanner;

public class MenuVentas {

    public static void mostrarMenuVentas(Scanner scanner) {
        int opcion;
        do {
            System.out.println("=== Menú de Gestión de Ventas ===");
            System.out.println("1. Registrar Venta");
            System.out.println("2. Consultar Venta");
            System.out.println("3. Modificar Venta");
            System.out.println("4. Eliminar Venta");
            System.out.println("5. Listar Ventas");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    crearVenta(scanner);
                    break;
                case 2:
                    leerVenta(scanner);
                    break;
                case 3:
                    actualizarVenta(scanner);
                    break;
                case 4:
                    eliminarVenta(scanner);
                    break;
                case 5:
                    listarVentas();
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

    public static void crearVenta(Scanner scanner) {
        System.out.println("Registrar Venta (por implementar)");
    }

    public static void leerVenta(Scanner scanner) {
        System.out.println("Consultar Venta (por implementar)");
    }

    public static void actualizarVenta(Scanner scanner) {
        System.out.println("Modificar Venta (por implementar)");
    }

    public static void eliminarVenta(Scanner scanner) {
        System.out.println("Eliminar Venta (por implementar)");
    }

    public static void listarVentas() {
        System.out.println("Listar Ventas (por implementar)");
    }
}
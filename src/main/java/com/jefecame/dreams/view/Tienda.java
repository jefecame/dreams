package com.jefecame.dreams.view;

/* Modelos */
import com.jefecame.dreams.model.Cliente;
import com.jefecame.dreams.model.Venta;
import com.jefecame.dreams.model.Producto;

/* Modelos (Herencia) */
import com.jefecame.dreams.model.ProductoElectronica;
import com.jefecame.dreams.model.ProductoRopa;
import com.jefecame.dreams.model.DetalleVenta;
//import com.jefecame.dreams.model.EstadoVenta;

/* Utilidades */
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/* Servicios (Lógica de negocios) */
import com.jefecame.dreams.service.ClienteService;
import com.jefecame.dreams.service.ProductoService;
import com.jefecame.dreams.service.VentaService;

/**
 * Clase que maneja la interfaz de usuario basada en consola.
 * Proporciona menús interactivos para gestionar clientes, productos y ventas.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class Tienda {
    
    private ClienteService clienteService;
    private ProductoService productoService;
    private VentaService ventaService;
    private Scanner scanner;
    
    /**
     * Constructor que inicializa el menú con los servicios necesarios.
     * 
     * @param clienteService servicio de gestión de clientes
     * @param productoService servicio de gestión de productos
     * @param ventaService servicio de gestión de ventas
     */
    public Tienda(ClienteService clienteService, ProductoService productoService, VentaService ventaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.ventaService = ventaService;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Constructor por defecto que inicializa todos los servicios.
     */
    public Tienda() {
        this.clienteService = new ClienteService();
        this.productoService = new ProductoService();
        this.ventaService = new VentaService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Muestra el menú principal y gestiona la navegación.
     */
    public void mostrarMenuPrincipal() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("       DREAMS - Sistema de Tienda Departamental");
            System.out.println("=".repeat(50));
            System.out.println("1. Gestionar Clientes");
            System.out.println("2. Gestionar Productos");
            System.out.println("3. Realizar Venta");
            System.out.println("4. Ver Ventas");
            System.out.println("0. Salir");
            System.out.println("=".repeat(50));
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        gestionarClientes();
                        break;
                    case 2:
                        gestionarProductos();
                        break;
                    case 3:
                        realizarVenta();
                        break;
                    case 4:
                        mostrarVentas();
                        break;
                    case 0:
                        continuar = false;
                        System.out.println("\n¡Gracias por usar Dreams Sistema de Tienda!");
                        break;
                    default:
                        System.out.println("\nOpción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
    
    /**
     * Gestiona las operaciones relacionadas con clientes.
     */
    public void gestionarClientes() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        GESTIÓN DE CLIENTES");
            System.out.println("-".repeat(40));
            System.out.println("1. Agregar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente");
            System.out.println("4. Actualizar Cliente");
            System.out.println("5. Desactivar Cliente");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-".repeat(40));
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        agregarCliente();
                        break;
                    case 2:
                        listarClientes();
                        break;
                    case 3:
                        buscarCliente();
                        break;
                    case 4:
                        actualizarCliente();
                        break;
                    case 5:
                        desactivarCliente();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("\nOpción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
    
    /**
     * Gestiona las operaciones relacionadas con productos.
     */
    public void gestionarProductos() {
        boolean continuar = true;
        
        while (continuar) {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("        GESTIÓN DE PRODUCTOS");
            System.out.println("-".repeat(40));
            System.out.println("1. Agregar Producto Electrónico");
            System.out.println("2. Agregar Producto de Ropa");
            System.out.println("3. Listar Productos");
            System.out.println("4. Buscar Producto");
            System.out.println("5. Actualizar Stock");
            System.out.println("6. Desactivar Producto");
            System.out.println("0. Volver al Menú Principal");
            System.out.println("-".repeat(40));
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        agregarProductoElectronico();
                        break;
                    case 2:
                        agregarProductoRopa();
                        break;
                    case 3:
                        listarProductos();
                        break;
                    case 4:
                        buscarProducto();
                        break;
                    case 5:
                        actualizarStock();
                        break;
                    case 6:
                        desactivarProducto();
                        break;
                    case 0:
                        continuar = false;
                        break;
                    default:
                        System.out.println("\nOpción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nPor favor, ingrese un número válido.");
            } catch (Exception e) {
                System.out.println("\nError: " + e.getMessage());
            }
        }
    }
    
    /**
     * Realiza el proceso de venta interactivo.
     */
    public void realizarVenta() {
        try {
            System.out.println("\n" + "-".repeat(40));
            System.out.println("           REALIZAR VENTA");
            System.out.println("-".repeat(40));
            
            // Mostrar clientes disponibles
            List<Cliente> clientes = clienteService.obtenerClientesActivos();
            if (clientes.isEmpty()) {
                System.out.println("No hay clientes registrados. Debe crear al menos un cliente primero.");
                return;
            }
            
            System.out.println("\nClientes disponibles:");
            for (Cliente cliente : clientes) {
                System.out.printf("%d. %s (%s)%n", cliente.getId(), cliente.getNombre(), cliente.getEmail());
            }
            
            System.out.print("Seleccione el ID del cliente: ");
            int idCliente = Integer.parseInt(scanner.nextLine());
            
            Cliente cliente = clienteService.buscarCliente(idCliente);
            if (cliente == null || !cliente.isActivo()) {
                System.out.println("Cliente no encontrado o inactivo.");
                return;
            }
            
            // Iniciar nueva venta
            Venta venta = ventaService.iniciarNuevaVenta(cliente);
            System.out.println("\\nVenta iniciada. ID de venta: " + venta.getId());
            
            // Agregar productos a la venta
            boolean agregarMasProductos = true;
            while (agregarMasProductos) {
                List<Producto> productos = productoService.obtenerProductosConStock();
                if (productos.isEmpty()) {
                    System.out.println("No hay productos disponibles.");
                    break;
                }
                
                System.out.println("\\nProductos disponibles:");
                for (Producto producto : productos) {
                    System.out.println(producto.mostrarDetalles());
                }
                
                System.out.print("\\nIngrese el ID del producto (0 para finalizar): ");
                int idProducto = Integer.parseInt(scanner.nextLine());
                
                if (idProducto == 0) {
                    agregarMasProductos = false;
                    continue;
                }
                
                Producto producto = productoService.buscarProducto(idProducto);
                if (producto == null || !producto.isActivo()) {
                    System.out.println("Producto no encontrado o inactivo.");
                    continue;
                }
                
                System.out.print("Ingrese la cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                
                if (ventaService.agregarProductoAVenta(venta, producto, cantidad)) {
                    System.out.println("Producto agregado a la venta.");
                } else {
                    System.out.println("No se pudo agregar el producto a la venta.");
                }
                
                System.out.print("¿Desea agregar otro producto? (s/n): ");
                String respuesta = scanner.nextLine().toLowerCase();
                agregarMasProductos = respuesta.startsWith("s");
            }
            
            // Mostrar resumen y finalizar venta
            if (!venta.getProductosVenta().isEmpty()) {
                mostrarResumenVenta(venta);
                System.out.print("\\n¿Desea finalizar la venta? (s/n): ");
                String respuesta = scanner.nextLine().toLowerCase();
                
                if (respuesta.startsWith("s")) {
                    if (ventaService.finalizarVenta(venta)) {
                        System.out.println("¡Venta finalizada exitosamente!");
                        System.out.printf("Total: $%.2f%n", venta.getTotal());
                    } else {
                        System.out.println("Error al finalizar la venta.");
                    }
                } else {
                    ventaService.cancelarVenta(venta);
                    System.out.println("Venta cancelada.");
                }
            } else {
                ventaService.cancelarVenta(venta);
                System.out.println("No se agregaron productos. Venta cancelada.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Métodos auxiliares para las operaciones de clientes
    
    private void agregarCliente() {
        try {
            System.out.print("Ingrese el nombre del cliente: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Ingrese el email del cliente: ");
            String email = scanner.nextLine();
            
            Cliente cliente = clienteService.agregarCliente(nombre, email);
            System.out.println("Cliente agregado exitosamente. ID: " + cliente.getId());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void listarClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        
        System.out.println("\\nLista de clientes:");
        System.out.println("-".repeat(60));
        System.out.printf("%-5s %-20s %-25s %-10s%n", "ID", "Nombre", "Email", "Estado");
        System.out.println("-".repeat(60));
        
        for (Cliente cliente : clientes) {
            System.out.printf("%-5d %-20s %-25s %-10s%n", 
                cliente.getId(), 
                cliente.getNombre(), 
                cliente.getEmail(),
                cliente.isActivo() ? "Activo" : "Inactivo");
        }
    }
    
    private void buscarCliente() {
        try {
            System.out.print("Ingrese el ID del cliente: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Cliente cliente = clienteService.buscarCliente(id);
            if (cliente != null) {
                System.out.println("\\nCliente encontrado:");
                System.out.printf("ID: %d%n", cliente.getId());
                System.out.printf("Nombre: %s%n", cliente.getNombre());
                System.out.printf("Email: %s%n", cliente.getEmail());
                System.out.printf("Estado: %s%n", cliente.isActivo() ? "Activo" : "Inactivo");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        }
    }
    
    private void actualizarCliente() {
        try {
            System.out.print("Ingrese el ID del cliente a actualizar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Cliente cliente = clienteService.buscarCliente(id);
            if (cliente == null) {
                System.out.println("Cliente no encontrado.");
                return;
            }
            
            System.out.printf("Nombre actual: %s%n", cliente.getNombre());
            System.out.print("Ingrese el nuevo nombre (Enter para mantener): ");
            String nombre = scanner.nextLine();
            if (nombre.trim().isEmpty()) {
                nombre = cliente.getNombre();
            }
            
            System.out.printf("Email actual: %s%n", cliente.getEmail());
            System.out.print("Ingrese el nuevo email (Enter para mantener): ");
            String email = scanner.nextLine();
            if (email.trim().isEmpty()) {
                email = cliente.getEmail();
            }
            
            if (clienteService.actualizarCliente(id, nombre, email)) {
                System.out.println("Cliente actualizado exitosamente.");
            } else {
                System.out.println("Error al actualizar el cliente.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void desactivarCliente() {
        try {
            System.out.print("Ingrese el ID del cliente a desactivar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            if (clienteService.desactivarCliente(id)) {
                System.out.println("Cliente desactivado exitosamente.");
            } else {
                System.out.println("Cliente no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        }
    }
    
    // Métodos auxiliares para las operaciones de productos
    
    private void agregarProductoElectronico() {
        try {
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Ingrese el precio: ");
            BigDecimal precio = new BigDecimal(scanner.nextLine());
            
            System.out.print("Ingrese el stock inicial: ");
            int stock = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese la marca: ");
            String marca = scanner.nextLine();
            
            System.out.print("Ingrese el modelo: ");
            String modelo = scanner.nextLine();
            
            ProductoElectronica producto = new ProductoElectronica(nombre, precio, stock, marca, modelo);
            productoService.agregarProducto(producto);
            System.out.println("Producto electrónico agregado exitosamente. ID: " + producto.getId());
            
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los números ingresados.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void agregarProductoRopa() {
        try {
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            
            System.out.print("Ingrese el precio: ");
            BigDecimal precio = new BigDecimal(scanner.nextLine());
            
            System.out.print("Ingrese el stock inicial: ");
            int stock = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese la talla: ");
            String talla = scanner.nextLine();
            
            System.out.print("Ingrese el color: ");
            String color = scanner.nextLine();
            
            ProductoRopa producto = new ProductoRopa(nombre, precio, stock, talla, color);
            productoService.agregarProducto(producto);
            System.out.println("Producto de ropa agregado exitosamente. ID: " + producto.getId());
            
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de los números ingresados.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void listarProductos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        
        System.out.println("\\nLista de productos:");
        System.out.println("-".repeat(80));
        for (Producto producto : productos) {
            System.out.println(producto.mostrarDetalles());
            System.out.println("Estado: " + (producto.isActivo() ? "Activo" : "Inactivo"));
            System.out.println("-".repeat(80));
        }
    }
    
    private void buscarProducto() {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            Producto producto = productoService.buscarProducto(id);
            if (producto != null) {
                System.out.println("\\nProducto encontrado:");
                System.out.println(producto.mostrarDetalles());
                System.out.println("Estado: " + (producto.isActivo() ? "Activo" : "Inactivo"));
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        }
    }
    
    private void actualizarStock() {
        try {
            System.out.print("Ingrese el ID del producto: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese la cantidad a agregar (negativa para restar): ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            if (productoService.actualizarStock(id, cantidad)) {
                System.out.println("Stock actualizado exitosamente.");
                Producto producto = productoService.buscarProducto(id);
                if (producto != null) {
                    System.out.println("Stock actual: " + producto.getStock());
                }
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese números válidos.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private void desactivarProducto() {
        try {
            System.out.print("Ingrese el ID del producto a desactivar: ");
            int id = Integer.parseInt(scanner.nextLine());
            
            if (productoService.desactivarProducto(id)) {
                System.out.println("Producto desactivado exitosamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un ID válido.");
        }
    }
    
    private void mostrarVentas() {
        List<Venta> ventas = ventaService.obtenerTodasLasVentas();
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        
        System.out.println("\\nLista de ventas:");
        System.out.println("-".repeat(80));
        System.out.printf("%-5s %-20s %-15s %-15s %-12s%n", "ID", "Cliente", "Fecha", "Total", "Estado");
        System.out.println("-".repeat(80));
        
        for (Venta venta : ventas) {
            System.out.printf("%-5d %-20s %-15s $%-14.2f %-12s%n", 
                venta.getId(),
                venta.getCliente() != null ? venta.getCliente().getNombre() : "N/A",
                venta.getFechaCreacion().toString().substring(0, 16),
                venta.getTotal(),
                venta.getEstado());
        }
    }
    
    private void mostrarResumenVenta(Venta venta) {
        System.out.println("\\n" + "=".repeat(50));
        System.out.println("           RESUMEN DE VENTA");
        System.out.println("=".repeat(50));
        System.out.printf("ID Venta: %d%n", venta.getId());
        System.out.printf("Cliente: %s%n", venta.getCliente().getNombre());
        System.out.printf("Fecha: %s%n", venta.getFechaCreacion());
        System.out.println("-".repeat(50));
        
        for (DetalleVenta detalle : venta.getProductosVenta()) {
            System.out.printf("%-25s x%d  $%.2f%n", 
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getSubtotal());
        }
        
        System.out.println("-".repeat(50));
        System.out.printf("Subtotal: $%.2f%n", venta.getSubtotal());
        System.out.printf("IVA (16%%): $%.2f%n", venta.getImpuestos());
        System.out.printf("TOTAL: $%.2f%n", venta.getTotal());
        System.out.println("=".repeat(50));
    }
}

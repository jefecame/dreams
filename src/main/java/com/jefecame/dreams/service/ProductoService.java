package com.jefecame.dreams.service;

import com.jefecame.dreams.model.Producto;
import com.jefecame.dreams.repository.ProductoRepository;
import java.math.BigDecimal;
import java.util.List;

/**
 * Servicio para la gestión de productos.
 * Contiene la lógica de negocio relacionada con los productos.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoService {
    
    private ProductoRepository productoRepository;
    
    /**
     * Constructor que inicializa el servicio con un repositorio de productos.
     * 
     * @param productoRepository repositorio de productos a utilizar
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    /**
     * Constructor por defecto que inicializa el repositorio.
     */
    public ProductoService() {
        this.productoRepository = new ProductoRepository();
    }
    
    /**
     * Agrega un nuevo producto al sistema.
     * 
     * @param producto producto a agregar
     * @throws IllegalArgumentException si el producto es inválido
     */
    public void agregarProducto(Producto producto) {
        // Validaciones
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a 0");
        }
        
        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock del producto no puede ser negativo");
        }
        
        // Verificar si ya existe un producto con el mismo nombre
        List<Producto> productosExistentes = productoRepository.obtenerTodos();
        boolean nombreExiste = productosExistentes.stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(producto.getNombre().trim()) && p.getId() != producto.getId());
        
        if (nombreExiste) {
            throw new IllegalArgumentException("Ya existe un producto con este nombre");
        }
        
        // Guardar el producto
        productoRepository.guardar(producto);
    }
    
    /**
     * Elimina un producto del sistema.
     * 
     * @param id identificador del producto a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminarProducto(int id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            return productoRepository.eliminar(id);
        }
        return false;
    }
    
    /**
     * Busca un producto por su identificador.
     * 
     * @param id identificador del producto a buscar
     * @return el producto encontrado o null si no existe
     */
    public Producto buscarProducto(int id) {
        return productoRepository.buscarPorId(id);
    }
    
    /**
     * Actualiza el stock de un producto.
     * 
     * @param idProducto identificador del producto
     * @param cantidad cantidad a agregar (positiva) o quitar (negativa)
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws IllegalArgumentException si la cantidad resultante sería negativa
     */
    public boolean actualizarStock(int idProducto, int cantidad) {
        Producto producto = productoRepository.buscarPorId(idProducto);
        if (producto == null) {
            return false;
        }
        
        int nuevoStock = producto.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("El stock resultante no puede ser negativo. Stock actual: " + 
                                             producto.getStock() + ", cantidad solicitada: " + cantidad);
        }
        
        producto.actualizarStock(cantidad);
        productoRepository.guardar(producto);
        
        return true;
    }
    
    /**
     * Obtiene todos los productos del sistema.
     * 
     * @return lista de todos los productos
     */
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.obtenerTodos();
    }
    
    /**
     * Obtiene todos los productos activos del sistema.
     * 
     * @return lista de productos activos
     */
    public List<Producto> obtenerProductosActivos() {
        return productoRepository.obtenerProductosActivos();
    }
    
    /**
     * Obtiene todos los productos con stock disponible.
     * 
     * @return lista de productos con stock mayor a 0
     */
    public List<Producto> obtenerProductosConStock() {
        return productoRepository.obtenerProductosConStock();
    }
    
    /**
     * Busca productos por nombre.
     * 
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden con el nombre
     */
    public List<Producto> buscarProductosPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de búsqueda no puede estar vacío");
        }
        
        return productoRepository.buscarPorNombre(nombre);
    }
    
    /**
     * Actualiza la información de un producto.
     * 
     * @param id identificador del producto a actualizar
     * @param nombre nuevo nombre del producto
     * @param precio nuevo precio del producto
     * @return true si se actualizó correctamente, false en caso contrario
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public boolean actualizarProducto(int id, String nombre, BigDecimal precio) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto == null) {
            return false;
        }
        
        // Validaciones
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }
        
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a 0");
        }
        
        // Verificar si el nombre ya está en uso por otro producto
        List<Producto> productosExistentes = productoRepository.obtenerTodos();
        boolean nombreExiste = productosExistentes.stream()
                .anyMatch(p -> p.getId() != id && p.getNombre().equalsIgnoreCase(nombre.trim()));
        
        if (nombreExiste) {
            throw new IllegalArgumentException("Ya existe otro producto con este nombre");
        }
        
        // Actualizar información
        producto.setNombre(nombre.trim());
        producto.setPrecio(precio);
        productoRepository.guardar(producto);
        
        return true;
    }
    
    /**
     * Desactiva un producto (soft delete).
     * 
     * @param id identificador del producto a desactivar
     * @return true si se desactivó correctamente, false en caso contrario
     */
    public boolean desactivarProducto(int id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            producto.setActivo(false);
            productoRepository.guardar(producto);
            return true;
        }
        return false;
    }
    
    /**
     * Reactiva un producto.
     * 
     * @param id identificador del producto a reactivar
     * @return true si se reactivó correctamente, false en caso contrario
     */
    public boolean reactivarProducto(int id) {
        Producto producto = productoRepository.buscarPorId(id);
        if (producto != null) {
            producto.setActivo(true);
            productoRepository.guardar(producto);
            return true;
        }
        return false;
    }
    
    /**
     * Valida la disponibilidad de un producto para una venta.
     * 
     * @param idProducto identificador del producto
     * @param cantidadRequerida cantidad requerida
     * @return true si hay suficiente stock y el producto está activo, false en caso contrario
     */
    public boolean validarDisponibilidad(int idProducto, int cantidadRequerida) {
        Producto producto = productoRepository.buscarPorId(idProducto);
        if (producto == null) {
            return false;
        }
        
        return producto.validarDisponibilidad(cantidadRequerida);
    }
}

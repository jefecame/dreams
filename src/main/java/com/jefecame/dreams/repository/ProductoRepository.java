package com.jefecame.dreams.repository;

import com.jefecame.dreams.model.Producto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar los datos de los productos.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class ProductoRepository {
    
    private List<Producto> productos;
    
    /**
     * Constructor que inicializa la lista de productos.
     */
    public ProductoRepository() {
        this.productos = new ArrayList<>();
    }
    
    /**
     * Guarda un producto en el repositorio.
     * 
     * @param producto producto a guardar
     */
    public void guardar(Producto producto) {
        if (producto != null) {
            // Verificar si ya existe un producto con el mismo ID
            Optional<Producto> productoExistente = productos.stream()
                    .filter(p -> p.getId() == producto.getId())
                    .findFirst();
            
            if (productoExistente.isPresent()) {
                // Actualizar producto existente
                int index = productos.indexOf(productoExistente.get());
                productos.set(index, producto);
            } else {
                // Agregar nuevo producto
                productos.add(producto);
            }
        }
    }
    
    /**
     * Elimina un producto del repositorio por su ID.
     * 
     * @param id identificador del producto a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminar(int id) {
        return productos.removeIf(producto -> producto.getId() == id);
    }
    
    /**
     * Busca un producto por su ID.
     * 
     * @param id identificador del producto a buscar
     * @return el producto encontrado o null si no existe
     */
    public Producto buscarPorId(int id) {
        return productos.stream()
                .filter(producto -> producto.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todos los productos del repositorio.
     * 
     * @return lista de todos los productos
     */
    public List<Producto> obtenerTodos() {
        return new ArrayList<>(productos);
    }
    
    /**
     * Obtiene el número total de productos en el repositorio.
     * 
     * @return número total de productos
     */
    public int contarProductos() {
        return productos.size();
    }
    
    /**
     * Obtiene todos los productos activos.
     * 
     * @return lista de productos activos
     */
    public List<Producto> obtenerProductosActivos() {
        return productos.stream()
                .filter(Producto::isActivo)
                .toList();
    }
    
    /**
     * Obtiene todos los productos con stock disponible.
     * 
     * @return lista de productos con stock mayor a 0
     */
    public List<Producto> obtenerProductosConStock() {
        return productos.stream()
                .filter(producto -> producto.getStock() > 0 && producto.isActivo())
                .toList();
    }
    
    /**
     * Busca productos por nombre (búsqueda parcial).
     * 
     * @param nombre nombre o parte del nombre a buscar
     * @return lista de productos que coinciden con el nombre
     */
    public List<Producto> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        return productos.stream()
                .filter(producto -> producto.getNombre().toLowerCase()
                        .contains(nombre.toLowerCase().trim()))
                .toList();
    }
}

package com.jefecame.dreams.model;

/**
 * Enumeración que define las categorías de productos disponibles en el sistema.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public enum CategoriaProducto {
    
    /**
     * Productos electrónicos como computadoras, teléfonos, etc.
     */
    ELECTRONICA("Electrónica", "Productos electrónicos y tecnológicos"),
    
    /**
     * Productos de vestir y accesorios.
     */
    ROPA("Ropa", "Prendas de vestir y accesorios"),
    
    /**
     * Productos de hogar y decoración.
     */
    HOGAR("Hogar", "Productos para el hogar y decoración"),
    
    /**
     * Productos deportivos y de recreación.
     */
    DEPORTES("Deportes", "Artículos deportivos y recreativos"),
    
    /**
     * Productos de belleza y cuidado personal.
     */
    BELLEZA("Belleza", "Productos de belleza y cuidado personal"),
    
    /**
     * Libros y material educativo.
     */
    LIBROS("Libros", "Libros y material educativo"),
    
    /**
     * Juguetes y productos para niños.
     */
    JUGUETES("Juguetes", "Juguetes y productos para niños"),
    
    /**
     * Productos alimenticios.
     */
    ALIMENTOS("Alimentos", "Productos alimenticios"),
    
    /**
     * Productos automotrices.
     */
    AUTOMOTRIZ("Automotriz", "Productos y accesorios automotrices"),
    
    /**
     * Otros productos no clasificados.
     */
    OTROS("Otros", "Otros productos no clasificados");
    
    private final String nombre;
    private final String descripcion;
    
    /**
     * Constructor del enum CategoriaProducto.
     * 
     * @param nombre el nombre de la categoría
     * @param descripcion la descripción de la categoría
     */
    CategoriaProducto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    
    /**
     * Obtiene el nombre de la categoría.
     * 
     * @return el nombre de la categoría
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Obtiene la descripción de la categoría.
     * 
     * @return la descripción de la categoría
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * Convierte un string a la categoría correspondiente.
     * 
     * @param categoria el string de la categoría
     * @return la categoría correspondiente o OTROS si no existe
     */
    public static CategoriaProducto fromString(String categoria) {
        if (categoria == null) return OTROS;
        
        for (CategoriaProducto c : CategoriaProducto.values()) {
            if (c.nombre.equalsIgnoreCase(categoria) || c.name().equalsIgnoreCase(categoria)) {
                return c;
            }
        }
        return OTROS;
    }
    
    /**
     * Obtiene todas las categorías disponibles como array de strings.
     * 
     * @return array con los nombres de todas las categorías
     */
    public static String[] getNombres() {
        CategoriaProducto[] categorias = values();
        String[] nombres = new String[categorias.length];
        for (int i = 0; i < categorias.length; i++) {
            nombres[i] = categorias[i].getNombre();
        }
        return nombres;
    }
    
    @Override
    public String toString() {
        return nombre;
    }
}

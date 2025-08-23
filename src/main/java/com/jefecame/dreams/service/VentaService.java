package com.jefecame.dreams.service;

import com.jefecame.dreams.model.Venta;
import com.jefecame.dreams.model.Cliente;
import com.jefecame.dreams.model.Producto;
import com.jefecame.dreams.model.EstadoVenta;
import com.jefecame.dreams.repository.VentaRepository;
import com.jefecame.dreams.repository.ProductoRepository;
import com.jefecame.dreams.repository.ClienteRepository;
import java.util.List;

/**
 * Servicio para la gestión de ventas.
 * Contiene la lógica de negocio relacionada con las ventas.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class VentaService {
    
    private VentaRepository ventaRepository;
    private ProductoRepository productoRepository;
    //private ClienteRepository clienteRepository;
    
    /**
     * Constructor que inicializa el servicio con los repositorios necesarios.
     * 
     * @param ventaRepository repositorio de ventas
     * @param productoRepository repositorio de productos
     * @param clienteRepository repositorio de clientes
     */
    public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }
    
    /**
     * Constructor por defecto que inicializa todos los repositorios.
     */
    public VentaService() {
        this.ventaRepository = new VentaRepository();
        this.productoRepository = new ProductoRepository();
        this.clienteRepository = new ClienteRepository();
    }
    
    /**
     * Inicia una nueva venta para un cliente específico.
     * 
     * @param cliente cliente que realiza la compra
     * @return la venta creada
     * @throws IllegalArgumentException si el cliente es inválido
     */
    public Venta iniciarNuevaVenta(Cliente cliente) {
        // Validaciones
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        
        if (!cliente.isActivo()) {
            throw new IllegalArgumentException("No se puede crear una venta para un cliente inactivo");
        }
        
        // Crear la nueva venta
        Venta nuevaVenta = new Venta(cliente);
        ventaRepository.guardar(nuevaVenta);
        
        return nuevaVenta;
    }
    
    /**
     * Agrega un producto a una venta existente.
     * 
     * @param venta venta a la cual agregar el producto
     * @param producto producto a agregar
     * @param cantidad cantidad del producto
     * @return true si se agregó correctamente, false en caso contrario
     * @throws IllegalArgumentException si los parámetros son inválidos
     */
    public boolean agregarProductoAVenta(Venta venta, Producto producto, int cantidad) {
        // Validaciones
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        
        if (venta.getEstado() != EstadoVenta.PROCESANDO) {
            throw new IllegalArgumentException("Solo se pueden agregar productos a ventas en estado PROCESANDO");
        }
        
        if (!producto.isActivo()) {
            throw new IllegalArgumentException("No se puede agregar un producto inactivo a la venta");
        }
        
        if (!producto.validarDisponibilidad(cantidad)) {
            throw new IllegalArgumentException("Stock insuficiente. Stock disponible: " + producto.getStock());
        }
        
        // Agregar el producto a la venta
        boolean agregado = venta.agregarProducto(producto, cantidad);
        
        if (agregado) {
            // Guardar los cambios en los repositorios
            ventaRepository.guardar(venta);
            productoRepository.guardar(producto); // Actualizar el stock del producto
        }
        
        return agregado;
    }
    
    /**
     * Finaliza una venta, cambiando su estado a COMPLETADA.
     * 
     * @param venta venta a finalizar
     * @return true si se finalizó correctamente, false en caso contrario
     * @throws IllegalArgumentException si la venta es inválida
     */
    public boolean finalizarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        
        if (venta.getEstado() != EstadoVenta.PROCESANDO) {
            throw new IllegalArgumentException("Solo se pueden finalizar ventas en estado PROCESANDO");
        }
        
        if (venta.getProductosVenta().isEmpty()) {
            throw new IllegalArgumentException("No se puede finalizar una venta sin productos");
        }
        
        // Finalizar la venta
        boolean finalizada = venta.finalizarVenta();
        
        if (finalizada) {
            ventaRepository.guardar(venta);
        }
        
        return finalizada;
    }
    
    /**
     * Cancela una venta, devolviendo el stock de los productos.
     * 
     * @param venta venta a cancelar
     * @return true si se canceló correctamente, false en caso contrario
     * @throws IllegalArgumentException si la venta es inválida
     */
    public boolean cancelarVenta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        
        if (venta.getEstado() != EstadoVenta.PROCESANDO) {
            throw new IllegalArgumentException("Solo se pueden cancelar ventas en estado PROCESANDO");
        }
        
        // Cancelar la venta (esto devuelve automáticamente el stock)
        boolean cancelada = venta.cancelarVenta();
        
        if (cancelada) {
            ventaRepository.guardar(venta);
            
            // Guardar los productos actualizados (con stock devuelto)
            venta.getProductosVenta().forEach(detalle -> {
                if (detalle.getProducto() != null) {
                    productoRepository.guardar(detalle.getProducto());
                }
            });
        }
        
        return cancelada;
    }
    
    /**
     * Obtiene todas las ventas del sistema.
     * 
     * @return lista de todas las ventas
     */
    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepository.obtenerTodas();
    }
    
    /**
     * Obtiene todas las ventas por estado.
     * 
     * @param estado estado de las ventas a buscar
     * @return lista de ventas con el estado especificado
     */
    public List<Venta> obtenerVentasPorEstado(EstadoVenta estado) {
        return ventaRepository.obtenerVentasPorEstado(estado);
    }
    
    /**
     * Obtiene todas las ventas de un cliente específico.
     * 
     * @param cliente cliente del cual obtener las ventas
     * @return lista de ventas del cliente
     */
    public List<Venta> obtenerVentasPorCliente(Cliente cliente) {
        return ventaRepository.obtenerVentasPorCliente(cliente);
    }
    
    /**
     * Busca una venta por su identificador.
     * 
     * @param id identificador de la venta
     * @return la venta encontrada o null si no existe
     */
    public Venta buscarVenta(int id) {
        return ventaRepository.buscarPorId(id);
    }
    
    /**
     * Obtiene todas las ventas completadas.
     * 
     * @return lista de ventas completadas
     */
    public List<Venta> obtenerVentasCompletadas() {
        return ventaRepository.obtenerVentasCompletadas();
    }
    
    /**
     * Obtiene todas las ventas en proceso.
     * 
     * @return lista de ventas en proceso
     */
    public List<Venta> obtenerVentasEnProceso() {
        return ventaRepository.obtenerVentasEnProceso();
    }
    
    /**
     * Remover un producto de una venta.
     * 
     * @param venta venta de la cual remover el producto
     * @param idDetalleVenta identificador del detalle de venta a remover
     * @return true si se removió correctamente, false en caso contrario
     */
    public boolean removerProductoDeVenta(Venta venta, int idDetalleVenta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula");
        }
        
        if (venta.getEstado() != EstadoVenta.PROCESANDO) {
            throw new IllegalArgumentException("Solo se pueden remover productos de ventas en estado PROCESANDO");
        }
        
        boolean removido = venta.removerProducto(idDetalleVenta);
        
        if (removido) {
            ventaRepository.guardar(venta);
            
            // Actualizar los productos en el repositorio (stock devuelto)
            venta.getProductosVenta().forEach(detalle -> {
                if (detalle.getProducto() != null) {
                    productoRepository.guardar(detalle.getProducto());
                }
            });
        }
        
        return removido;
    }
}

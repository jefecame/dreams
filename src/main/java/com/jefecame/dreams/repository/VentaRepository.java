package com.jefecame.dreams.repository;

import com.jefecame.dreams.model.Venta;
import com.jefecame.dreams.model.EstadoVenta;
import com.jefecame.dreams.model.Cliente;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para gestionar los datos de las ventas.
 * 
 * @author jefecame
 * @version 1.0.0
 */
public class VentaRepository {
    
    private List<Venta> ventas;
    
    /**
     * Constructor que inicializa la lista de ventas.
     */
    public VentaRepository() {
        this.ventas = new ArrayList<>();
    }
    
    /**
     * Guarda una venta en el repositorio.
     * 
     * @param venta venta a guardar
     */
    public void guardar(Venta venta) {
        if (venta != null) {
            // Verificar si ya existe una venta con el mismo ID
            Optional<Venta> ventaExistente = ventas.stream()
                    .filter(v -> v.getId() == venta.getId())
                    .findFirst();
            
            if (ventaExistente.isPresent()) {
                // Actualizar venta existente
                int index = ventas.indexOf(ventaExistente.get());
                ventas.set(index, venta);
            } else {
                // Agregar nueva venta
                ventas.add(venta);
            }
        }
    }
    
    /**
     * Elimina una venta del repositorio por su ID.
     * 
     * @param id identificador de la venta a eliminar
     * @return true si se eliminó correctamente, false en caso contrario
     */
    public boolean eliminar(int id) {
        return ventas.removeIf(venta -> venta.getId() == id);
    }
    
    /**
     * Busca una venta por su ID.
     * 
     * @param id identificador de la venta a buscar
     * @return la venta encontrada o null si no existe
     */
    public Venta buscarPorId(int id) {
        return ventas.stream()
                .filter(venta -> venta.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtiene todas las ventas del repositorio.
     * 
     * @return lista de todas las ventas
     */
    public List<Venta> obtenerTodas() {
        return new ArrayList<>(ventas);
    }
    
    /**
     * Obtiene el número total de ventas en el repositorio.
     * 
     * @return número total de ventas
     */
    public int contarVentas() {
        return ventas.size();
    }
    
    /**
     * Obtiene todas las ventas por estado.
     * 
     * @param estado estado de las ventas a filtrar
     * @return lista de ventas con el estado especificado
     */
    public List<Venta> obtenerVentasPorEstado(EstadoVenta estado) {
        return ventas.stream()
                .filter(venta -> venta.getEstado() == estado)
                .toList();
    }
    
    /**
     * Obtiene todas las ventas de un cliente específico.
     * 
     * @param cliente cliente del cual obtener las ventas
     * @return lista de ventas del cliente
     */
    public List<Venta> obtenerVentasPorCliente(Cliente cliente) {
        if (cliente == null) {
            return new ArrayList<>();
        }
        
        return ventas.stream()
                .filter(venta -> venta.getCliente() != null && 
                               venta.getCliente().getId() == cliente.getId())
                .toList();
    }
    
    /**
     * Obtiene ventas en un rango de fechas.
     * 
     * @param fechaInicio fecha de inicio del rango
     * @param fechaFin fecha de fin del rango
     * @return lista de ventas en el rango especificado
     */
    public List<Venta> obtenerVentasPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return new ArrayList<>();
        }
        
        return ventas.stream()
                .filter(venta -> venta.getFechaCreacion().isAfter(fechaInicio.minusSeconds(1)) && 
                               venta.getFechaCreacion().isBefore(fechaFin.plusSeconds(1)))
                .toList();
    }
    
    /**
     * Obtiene todas las ventas completadas.
     * 
     * @return lista de ventas completadas
     */
    public List<Venta> obtenerVentasCompletadas() {
        return obtenerVentasPorEstado(EstadoVenta.COMPLETADA);
    }
    
    /**
     * Obtiene todas las ventas en proceso.
     * 
     * @return lista de ventas en proceso
     */
    public List<Venta> obtenerVentasEnProceso() {
        return obtenerVentasPorEstado(EstadoVenta.PROCESANDO);
    }
}

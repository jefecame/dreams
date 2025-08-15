# Dreams - Mermaid UML Class Diagram

## System Overview
Dreams is a point-of-sale system that manages customers, products, and sales transactions. The system demonstrates object-oriented programming principles including inheritance, encapsulation, and polymorphism.

## Class Diagram

```mermaid
classDiagram
    %% Main Application Package
    class App {
        - Logger logger
        + main(String[] args) void
    }

    %% View Package
    class Dreams {
        + Dreams()
        + mostrarMenuPrincipal() void
        + crearCliente(Cliente) void
        + eliminarCliente(String) void
        + buscarCliente(String) Cliente
        + crearProducto(Producto) void
        + eliminarProducto(String) void
        + buscarProducto(String) Producto
        + crearVenta(Venta) void
        + listarVentas() void
    }

    %% Model Package - Core Classes
    class Cliente {
        - UUID id
        - String nombre
        - String email
        - boolean activo
        - LocalDateTime fechaCreacion
        - LocalDateTime fechaUltimaActualizacion
        - Pattern EMAIL_PATTERN
        + Cliente(String, String)
        + getId() UUID
        + getNombre() String
        + getEmail() String
        + isActivo() boolean
        + getFechaCreacion() LocalDateTime
        + getFechaUltimaActualizacion() LocalDateTime
        + setNombre(String) void
        + setEmail(String) void
        + setActivo(boolean) void
        + actualizarInformacion(String, String, boolean) void
        + equals(Object) boolean
        + hashCode() int
        + toString() String
    }

    class Producto {
        <<abstract>>
        # int id
        # String nombre
        # BigDecimal precio
        # CategoriaProducto categoria
        # int stock
        # boolean activo
        # LocalDateTime fechaCreacion
        # LocalDateTime fechaUltimaActualizacion
        - int contadorId
        + Producto(String, BigDecimal, CategoriaProducto, int)
        + getId() int
        + getNombre() String
        + getPrecio() BigDecimal
        + getCategoria() CategoriaProducto
        + getStock() int
        + isActivo() boolean
        + getFechaCreacion() LocalDateTime
        + getFechaUltimaActualizacion() LocalDateTime
        + setNombre(String) void
        + setPrecio(BigDecimal) void
        + setCategoria(CategoriaProducto) void
        + setStock(int) void
        + setActivo(boolean) void
        + actualizarStock(int) void
        + validarDisponibilidad(int) boolean
        + mostrarDetalles()* String
        + equals(Object) boolean
        + hashCode() int
        + toString() String
    }

    class ProductoElectronica {
        - String marca
        - String modelo
        - int garantiaMeses
        + ProductoElectronica(String, BigDecimal, int, String, String)
        + ProductoElectronica(String, BigDecimal, int, String, String, int)
        + getMarca() String
        + setMarca(String) void
        + getModelo() String
        + setModelo(String) void
        + getGarantiaMeses() int
        + setGarantiaMeses(int) void
        + mostrarDetalles() String
        + toString() String
    }

    class ProductoRopa {
        - String talla
        - String color
        - String material
        - String temporada
        + ProductoRopa(String, BigDecimal, int, String, String)
        + ProductoRopa(String, BigDecimal, int, String, String, String, String)
        + getTalla() String
        + setTalla(String) void
        + getColor() String
        + setColor(String) void
        + getMaterial() String
        + setMaterial(String) void
        + getTemporada() String
        + setTemporada(String) void
        + mostrarDetalles() String
        + toString() String
    }

    class Venta {
        - int id
        - UUID idCliente
        - List~ProductoVenta~ productosVenta
        - BigDecimal subtotal
        - BigDecimal impuestos
        - BigDecimal total
        - LocalDateTime fechaCreacion
        - LocalDateTime fechaUltimaActualizacion
        - EstadoVenta estado
        - BigDecimal tasaImpuesto
        - String comentarios
        - int contadorId
        - BigDecimal TASA_IMPUESTO_DEFAULT
        + Venta(UUID)
        + Venta(UUID, LocalDateTime)
        + getId() int
        + getIdCliente() UUID
        + getProductosVenta() List~ProductoVenta~
        + getSubtotal() BigDecimal
        + getImpuestos() BigDecimal
        + getTotal() BigDecimal
        + getFechaCreacion() LocalDateTime
        + getFechaUltimaActualizacion() LocalDateTime
        + getEstado() EstadoVenta
        + getTasaImpuesto() BigDecimal
        + getComentarios() String
        + setTasaImpuesto(BigDecimal) void
        + setComentarios(String) void
        + agregarProducto(Producto, int) boolean
        + removerProducto(int) boolean
        + actualizarCantidadProducto(int, int) boolean
        + calcularTotal() boolean
        + finalizarVenta() boolean
        + cancelarVenta() boolean
        + estaVacia() boolean
        + getCantidadProductos() int
        + getCantidadTotalUnidades() int
        + equals(Object) boolean
        + hashCode() int
        + toString() String
    }

    class ProductoVenta {
        - int id
        - int idProducto
        - int idVenta
        - int cantidad
        - BigDecimal precioUnitario
        - BigDecimal subtotal
        - LocalDateTime fechaCreacion
        - LocalDateTime fechaUltimaActualizacion
        - int contadorId
        + ProductoVenta(int, int, int, BigDecimal)
        + getId() int
        + getIdProducto() int
        + getIdVenta() int
        + getCantidad() int
        + getPrecioUnitario() BigDecimal
        + getSubtotal() BigDecimal
        + getFechaCreacion() LocalDateTime
        + getFechaUltimaActualizacion() LocalDateTime
        + setCantidad(int) void
        + setPrecioUnitario(BigDecimal) void
        + calcularSubtotal() BigDecimal
        + actualizar(int, BigDecimal) void
        + equals(Object) boolean
        + hashCode() int
        + toString() String
    }

    %% Enumerations
    class EstadoVenta {
        <<enumeration>>
        PROCESANDO
        COMPLETADA
        CANCELADA
        REEMBOLSO
        - String nombre
        - String descripcion
        + EstadoVenta(String, String)
        + getNombre() String
        + getDescripcion() String
        + fromString(String) EstadoVenta
        + toString() String
    }

    class CategoriaProducto {
        <<enumeration>>
        ELECTRONICA
        ROPA
        HOGAR
        DEPORTES
        BELLEZA
        LIBROS
        JUGUETES
        ALIMENTOS
        AUTOMOTRIZ
        OTROS
        - String nombre
        - String descripcion
        + CategoriaProducto(String, String)
        + getNombre() String
        + getDescripcion() String
        + fromString(String) CategoriaProducto
        + getNombres() String[]
        + toString() String
    }

    %% Relationships
    App --> Dreams : uses
    Dreams --> Cliente : manages
    Dreams --> Producto : manages
    Dreams --> Venta : manages
    
    %% Inheritance
    Producto <|-- ProductoElectronica : extends
    Producto <|-- ProductoRopa : extends
    
    %% Associations
    Cliente ||--o{ Venta : "1 to many"
    Venta ||--o{ ProductoVenta : "1 to many (composition)"
    ProductoVenta }o--|| Producto : "many to 1"
    Venta }o--|| EstadoVenta : "many to 1"
    Producto }o--|| CategoriaProducto : "many to 1"

    %% Package Grouping
    package "com.jefecame" {
        App
    }
    
    package "com.jefecame.dreams.view" {
        Dreams
    }
    
    package "com.jefecame.dreams.model" {
        Cliente
        Producto
        ProductoElectronica
        ProductoRopa
        Venta
        ProductoVenta
        EstadoVenta
        CategoriaProducto
    }
```

## Key Design Patterns and Principles

### 1. Inheritance (Herencia)
- **Abstract Class**: `Producto` is an abstract base class
- **Concrete Classes**: `ProductoElectronica` and `ProductoRopa` extend `Producto`
- **Template Method**: Abstract method `mostrarDetalles()` implemented differently by subclasses

### 2. Encapsulation (Encapsulamiento)
- Private and protected fields with controlled access through getters/setters
- Validation in setters and constructors
- Immutable fields (final) where appropriate (id, fechaCreacion)

### 3. Composition and Aggregation
- **Composition**: `Venta` contains `ProductoVenta` objects (strong relationship)
- **Aggregation**: `Venta` references `Cliente` by UUID (weak relationship)

### 4. Polymorphism (Polimorfismo)
- Abstract method `mostrarDetalles()` provides different implementations
- Enum methods with consistent interface

### 5. Domain-Driven Design
- Clear separation between model and view layers
- Rich domain objects with business logic
- Value objects (enumerations) with behavior

## Business Rules

1. **Cliente**: Manages customer information with email validation
2. **Producto**: Abstract base for all products with stock management
3. **Venta**: Manages sales transactions with automatic tax calculation
4. **ProductoVenta**: Junction table pattern for many-to-many relationship
5. **State Management**: Sales have defined states (PROCESANDO, COMPLETADA, etc.)

## Data Types Used

- **UUID**: For client identification (globally unique)
- **BigDecimal**: For monetary values (precision)
- **LocalDateTime**: For timestamp management
- **Enumerations**: For controlled vocabularies
- **Collections**: Lists for one-to-many relationships

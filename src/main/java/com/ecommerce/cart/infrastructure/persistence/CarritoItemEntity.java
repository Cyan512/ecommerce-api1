package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.product.infrastructure.persistence.ProductoEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "carrito_items")
public class CarritoItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "carrito_id", nullable = false)
    private UUID carritoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", insertable = false, updatable = false)
    private CarritoEntity carrito;

    @Column(name = "producto_id", nullable = false)
    private UUID productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", insertable = false, updatable = false)
    private ProductoEntity producto;

    @Column(nullable = false)
    private int cantidad;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCarritoId() { return carritoId; }
    public void setCarritoId(UUID carritoId) { this.carritoId = carritoId; }
    public CarritoEntity getCarrito() { return carrito; }
    public void setCarrito(CarritoEntity carrito) { this.carrito = carrito; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public ProductoEntity getProducto() { return producto; }
    public void setProducto(ProductoEntity producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

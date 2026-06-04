package com.ecommerce.cart.infrastructure.persistence;

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

    @Column(name = "producto_id", nullable = false)
    private UUID productoId;

    @Column(nullable = false)
    private int cantidad;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCarritoId() { return carritoId; }
    public void setCarritoId(UUID carritoId) { this.carritoId = carritoId; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

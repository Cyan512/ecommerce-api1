package com.ecommerce.cart.domain.model;

import java.util.UUID;

public class CarritoItem {

    private UUID id;
    private UUID carritoId;
    private UUID productoId;
    private int cantidad;

    public CarritoItem() {}

    public CarritoItem(UUID id, UUID carritoId, UUID productoId, int cantidad) {
        this.id = id;
        this.carritoId = carritoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getCarritoId() { return carritoId; }
    public void setCarritoId(UUID carritoId) { this.carritoId = carritoId; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

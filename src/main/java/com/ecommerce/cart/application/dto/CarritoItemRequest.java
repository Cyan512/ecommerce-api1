package com.ecommerce.cart.application.dto;

import jakarta.validation.constraints.Positive;

import java.util.UUID;

public class CarritoItemRequest {

    private UUID productoId;

    @Positive
    private int cantidad;

    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}

package com.ecommerce.order.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class PedidoItem {

    private UUID id;
    private UUID pedidoId;
    private UUID productoId;
    private int cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;

    public PedidoItem() {}

    public PedidoItem(UUID id, UUID pedidoId, UUID productoId, int cantidad,
                      BigDecimal precioUnitario, BigDecimal subtotal) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPedidoId() { return pedidoId; }
    public void setPedidoId(UUID pedidoId) { this.pedidoId = pedidoId; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
}

package com.ecommerce.cart.application.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CarritoResponse {

    private UUID id;
    private List<ItemResponse> items;
    private BigDecimal total;

    public CarritoResponse() {}

    public CarritoResponse(UUID id, List<ItemResponse> items, BigDecimal total) {
        this.id = id;
        this.items = items;
        this.total = total;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public List<ItemResponse> getItems() { return items; }
    public void setItems(List<ItemResponse> items) { this.items = items; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public static class ItemResponse {
        private UUID id;
        private UUID productoId;
        private String productoNombre;
        private int cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public ItemResponse() {}

        public ItemResponse(UUID id, UUID productoId, String productoNombre, int cantidad,
                            BigDecimal precioUnitario, BigDecimal subtotal) {
            this.id = id;
            this.productoId = productoId;
            this.productoNombre = productoNombre;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.subtotal = subtotal;
        }

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getProductoId() { return productoId; }
        public void setProductoId(UUID productoId) { this.productoId = productoId; }
        public String getProductoNombre() { return productoNombre; }
        public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
        public BigDecimal getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
        public BigDecimal getSubtotal() { return subtotal; }
        public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    }
}

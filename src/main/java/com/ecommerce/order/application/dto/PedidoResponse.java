package com.ecommerce.order.application.dto;

import com.ecommerce.order.domain.model.EstadoPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PedidoResponse {

    private UUID id;
    private String usuarioEmail;
    private String estado;
    private BigDecimal total;
    private LocalDateTime fechaCreacion;
    private List<ItemResponse> items;

    public PedidoResponse() {}

    public PedidoResponse(UUID id, String usuarioEmail, String estado, BigDecimal total, LocalDateTime fechaCreacion, List<ItemResponse> items) {
        this.id = id;
        this.usuarioEmail = usuarioEmail;
        this.estado = estado;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.items = items;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getUsuarioEmail() { return usuarioEmail; }
    public void setUsuarioEmail(String usuarioEmail) { this.usuarioEmail = usuarioEmail; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public List<ItemResponse> getItems() { return items; }
    public void setItems(List<ItemResponse> items) { this.items = items; }

    public static class ItemResponse {
        private UUID productoId;
        private String productoNombre;
        private int cantidad;
        private BigDecimal precioUnitario;
        private BigDecimal subtotal;

        public ItemResponse() {}

        public ItemResponse(UUID productoId, String productoNombre, int cantidad,
                            BigDecimal precioUnitario, BigDecimal subtotal) {
            this.productoId = productoId;
            this.productoNombre = productoNombre;
            this.cantidad = cantidad;
            this.precioUnitario = precioUnitario;
            this.subtotal = subtotal;
        }

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

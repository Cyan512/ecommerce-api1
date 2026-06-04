package com.ecommerce.order.application.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

public class PedidoRequest {

    private UUID direccionId;

    private String cuponCodigo;

    @NotEmpty
    private List<ItemRequest> items;

    public UUID getDireccionId() { return direccionId; }
    public void setDireccionId(UUID direccionId) { this.direccionId = direccionId; }
    public String getCuponCodigo() { return cuponCodigo; }
    public void setCuponCodigo(String cuponCodigo) { this.cuponCodigo = cuponCodigo; }
    public List<ItemRequest> getItems() { return items; }
    public void setItems(List<ItemRequest> items) { this.items = items; }

    public static class ItemRequest {
        private UUID productoId;
        private int cantidad;

        public UUID getProductoId() { return productoId; }
        public void setProductoId(UUID productoId) { this.productoId = productoId; }
        public int getCantidad() { return cantidad; }
        public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    }
}

package com.ecommerce.cart.application.dto;

import java.util.List;
import java.util.UUID;

public class WishlistResponse {

    private UUID id;
    private List<ItemResponse> items;

    public WishlistResponse() {}

    public WishlistResponse(UUID id, List<ItemResponse> items) {
        this.id = id;
        this.items = items;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public List<ItemResponse> getItems() { return items; }
    public void setItems(List<ItemResponse> items) { this.items = items; }

    public static class ItemResponse {
        private UUID id;
        private UUID productoId;
        private String productoNombre;

        public ItemResponse() {}

        public ItemResponse(UUID id, UUID productoId, String productoNombre) {
            this.id = id;
            this.productoId = productoId;
            this.productoNombre = productoNombre;
        }

        public UUID getId() { return id; }
        public void setId(UUID id) { this.id = id; }
        public UUID getProductoId() { return productoId; }
        public void setProductoId(UUID productoId) { this.productoId = productoId; }
        public String getProductoNombre() { return productoNombre; }
        public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }
    }
}

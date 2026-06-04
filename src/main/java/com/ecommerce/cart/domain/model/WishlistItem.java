package com.ecommerce.cart.domain.model;

import java.util.UUID;

public class WishlistItem {

    private UUID id;
    private UUID wishlistId;
    private UUID productoId;

    public WishlistItem() {}

    public WishlistItem(UUID id, UUID wishlistId, UUID productoId) {
        this.id = id;
        this.wishlistId = wishlistId;
        this.productoId = productoId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWishlistId() { return wishlistId; }
    public void setWishlistId(UUID wishlistId) { this.wishlistId = wishlistId; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
}

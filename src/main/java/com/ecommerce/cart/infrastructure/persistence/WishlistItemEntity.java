package com.ecommerce.cart.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "wishlist_items", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"wishlist_id", "producto_id"})
})
public class WishlistItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "wishlist_id", nullable = false)
    private UUID wishlistId;

    @Column(name = "producto_id", nullable = false)
    private UUID productoId;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWishlistId() { return wishlistId; }
    public void setWishlistId(UUID wishlistId) { this.wishlistId = wishlistId; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
}

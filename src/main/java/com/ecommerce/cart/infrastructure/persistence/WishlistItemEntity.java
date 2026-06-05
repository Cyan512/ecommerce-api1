package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.product.infrastructure.persistence.ProductoEntity;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id", insertable = false, updatable = false)
    private WishlistEntity wishlist;

    @Column(name = "producto_id", nullable = false)
    private UUID productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", insertable = false, updatable = false)
    private ProductoEntity producto;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getWishlistId() { return wishlistId; }
    public void setWishlistId(UUID wishlistId) { this.wishlistId = wishlistId; }
    public WishlistEntity getWishlist() { return wishlist; }
    public void setWishlist(WishlistEntity wishlist) { this.wishlist = wishlist; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public ProductoEntity getProducto() { return producto; }
    public void setProducto(ProductoEntity producto) { this.producto = producto; }
}

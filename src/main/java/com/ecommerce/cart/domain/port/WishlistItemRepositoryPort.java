package com.ecommerce.cart.domain.port;

import com.ecommerce.cart.domain.model.WishlistItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishlistItemRepositoryPort {

    List<WishlistItem> findByWishlistId(UUID wishlistId);

    Optional<WishlistItem> findByWishlistIdAndProductoId(UUID wishlistId, UUID productoId);

    WishlistItem save(WishlistItem item);

    void deleteById(UUID id);
}

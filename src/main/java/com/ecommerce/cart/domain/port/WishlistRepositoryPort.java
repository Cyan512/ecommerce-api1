package com.ecommerce.cart.domain.port;

import com.ecommerce.cart.domain.model.Wishlist;

import java.util.Optional;
import java.util.UUID;

public interface WishlistRepositoryPort {

    Optional<Wishlist> findByUsuarioId(UUID usuarioId);

    Wishlist save(Wishlist wishlist);

    void deleteById(UUID id);
}

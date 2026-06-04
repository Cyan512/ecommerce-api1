package com.ecommerce.cart.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WishlistItemJpaRepository extends JpaRepository<WishlistItemEntity, UUID> {

    List<WishlistItemEntity> findByWishlistId(UUID wishlistId);

    Optional<WishlistItemEntity> findByWishlistIdAndProductoId(UUID wishlistId, UUID productoId);
}

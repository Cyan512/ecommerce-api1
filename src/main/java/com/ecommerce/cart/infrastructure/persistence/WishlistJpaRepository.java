package com.ecommerce.cart.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WishlistJpaRepository extends JpaRepository<WishlistEntity, UUID> {

    Optional<WishlistEntity> findByUsuarioId(UUID usuarioId);
}

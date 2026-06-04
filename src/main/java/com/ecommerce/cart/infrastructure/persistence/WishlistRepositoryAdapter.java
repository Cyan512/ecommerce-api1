package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.cart.domain.model.Wishlist;
import com.ecommerce.cart.domain.port.WishlistRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class WishlistRepositoryAdapter implements WishlistRepositoryPort {

    private final WishlistJpaRepository jpaRepository;

    public WishlistRepositoryAdapter(WishlistJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Wishlist> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).map(this::toDomain);
    }

    @Override
    public Wishlist save(Wishlist wishlist) {
        return toDomain(jpaRepository.save(toEntity(wishlist)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Wishlist toDomain(WishlistEntity entity) {
        return new Wishlist(entity.getId(), entity.getUsuarioId());
    }

    private WishlistEntity toEntity(Wishlist w) {
        WishlistEntity entity = new WishlistEntity();
        entity.setId(w.getId());
        entity.setUsuarioId(w.getUsuarioId());
        return entity;
    }
}

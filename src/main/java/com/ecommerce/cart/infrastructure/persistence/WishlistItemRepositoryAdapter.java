package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.cart.domain.model.WishlistItem;
import com.ecommerce.cart.domain.port.WishlistItemRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class WishlistItemRepositoryAdapter implements WishlistItemRepositoryPort {

    private final WishlistItemJpaRepository jpaRepository;

    public WishlistItemRepositoryAdapter(WishlistItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<WishlistItem> findByWishlistId(UUID wishlistId) {
        return jpaRepository.findByWishlistId(wishlistId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<WishlistItem> findByWishlistIdAndProductoId(UUID wishlistId, UUID productoId) {
        return jpaRepository.findByWishlistIdAndProductoId(wishlistId, productoId).map(this::toDomain);
    }

    @Override
    public WishlistItem save(WishlistItem item) {
        return toDomain(jpaRepository.save(toEntity(item)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private WishlistItem toDomain(WishlistItemEntity entity) {
        return new WishlistItem(entity.getId(), entity.getWishlistId(), entity.getProductoId());
    }

    private WishlistItemEntity toEntity(WishlistItem item) {
        WishlistItemEntity entity = new WishlistItemEntity();
        entity.setId(item.getId());
        entity.setWishlistId(item.getWishlistId());
        entity.setProductoId(item.getProductoId());
        return entity;
    }
}

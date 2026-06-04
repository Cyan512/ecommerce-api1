package com.ecommerce.cart.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarritoItemJpaRepository extends JpaRepository<CarritoItemEntity, UUID> {

    List<CarritoItemEntity> findByCarritoId(UUID carritoId);

    Optional<CarritoItemEntity> findByCarritoIdAndProductoId(UUID carritoId, UUID productoId);

    void deleteByCarritoId(UUID carritoId);
}

package com.ecommerce.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CuponJpaRepository extends JpaRepository<CuponEntity, UUID> {

    Optional<CuponEntity> findByCodigo(String codigo);
}

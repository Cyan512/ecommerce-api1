package com.ecommerce.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EnvioJpaRepository extends JpaRepository<EnvioEntity, UUID> {

    Optional<EnvioEntity> findByPedidoId(UUID pedidoId);
}

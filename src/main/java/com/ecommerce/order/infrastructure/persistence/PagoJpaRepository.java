package com.ecommerce.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PagoJpaRepository extends JpaRepository<PagoEntity, UUID> {

    Optional<PagoEntity> findByPedidoId(UUID pedidoId);
}

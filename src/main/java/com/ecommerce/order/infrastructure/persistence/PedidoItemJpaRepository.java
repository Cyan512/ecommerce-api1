package com.ecommerce.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoItemJpaRepository extends JpaRepository<PedidoItemEntity, UUID> {

    List<PedidoItemEntity> findByPedidoId(UUID pedidoId);
}

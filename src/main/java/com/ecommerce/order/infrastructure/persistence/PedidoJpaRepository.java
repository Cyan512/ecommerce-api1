package com.ecommerce.order.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, UUID> {

    List<PedidoEntity> findByUsuarioId(UUID usuarioId);

    List<PedidoEntity> findByStaffId(UUID staffId);
}

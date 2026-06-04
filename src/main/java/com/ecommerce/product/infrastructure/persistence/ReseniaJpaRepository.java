package com.ecommerce.product.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReseniaJpaRepository extends JpaRepository<ReseniaEntity, UUID> {

    List<ReseniaEntity> findByProductoId(UUID productoId);

    List<ReseniaEntity> findByUsuarioId(UUID usuarioId);

    Optional<ReseniaEntity> findByUsuarioIdAndProductoId(UUID usuarioId, UUID productoId);
}

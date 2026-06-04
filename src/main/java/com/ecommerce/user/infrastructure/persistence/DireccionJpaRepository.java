package com.ecommerce.user.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DireccionJpaRepository extends JpaRepository<DireccionEntity, UUID> {

    List<DireccionEntity> findByUsuarioId(UUID usuarioId);
}

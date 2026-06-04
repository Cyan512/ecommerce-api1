package com.ecommerce.product.domain.port;

import com.ecommerce.product.domain.model.Resenia;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReseniaRepositoryPort {

    Resenia save(Resenia resenia);

    Optional<Resenia> findById(UUID id);

    List<Resenia> findByProductoId(UUID productoId);

    List<Resenia> findByUsuarioId(UUID usuarioId);

    Optional<Resenia> findByUsuarioIdAndProductoId(UUID usuarioId, UUID productoId);

    void deleteById(UUID id);
}

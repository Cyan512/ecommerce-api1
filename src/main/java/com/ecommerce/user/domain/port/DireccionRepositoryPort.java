package com.ecommerce.user.domain.port;

import com.ecommerce.user.domain.model.Direccion;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DireccionRepositoryPort {

    Direccion save(Direccion direccion);

    Optional<Direccion> findById(UUID id);

    List<Direccion> findByUsuarioId(UUID usuarioId);

    void deleteById(UUID id);
}

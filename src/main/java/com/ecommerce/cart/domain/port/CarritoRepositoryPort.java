package com.ecommerce.cart.domain.port;

import com.ecommerce.cart.domain.model.Carrito;

import java.util.Optional;
import java.util.UUID;

public interface CarritoRepositoryPort {

    Optional<Carrito> findByUsuarioId(UUID usuarioId);

    Carrito save(Carrito carrito);

    void deleteById(UUID id);
}

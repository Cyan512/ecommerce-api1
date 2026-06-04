package com.ecommerce.order.domain.port;

import com.ecommerce.order.domain.model.Envio;

import java.util.Optional;
import java.util.UUID;

public interface EnvioRepositoryPort {

    Envio save(Envio envio);

    Optional<Envio> findByPedidoId(UUID pedidoId);

    Optional<Envio> findById(UUID id);
}

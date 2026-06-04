package com.ecommerce.order.domain.port;

import com.ecommerce.order.domain.model.Pago;

import java.util.Optional;
import java.util.UUID;

public interface PagoRepositoryPort {

    Pago save(Pago pago);

    Optional<Pago> findByPedidoId(UUID pedidoId);

    Optional<Pago> findById(UUID id);
}

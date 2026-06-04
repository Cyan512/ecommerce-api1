package com.ecommerce.order.domain.port;

import com.ecommerce.order.domain.model.PedidoItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoItemRepositoryPort {

    List<PedidoItem> findByPedidoId(UUID pedidoId);

    PedidoItem save(PedidoItem item);

    Optional<PedidoItem> findById(UUID id);
}

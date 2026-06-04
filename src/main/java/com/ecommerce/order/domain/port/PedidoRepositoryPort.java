package com.ecommerce.order.domain.port;

import com.ecommerce.order.domain.model.Pedido;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {

    List<Pedido> findAll();

    Optional<Pedido> findById(UUID id);

    List<Pedido> findByUsuarioId(UUID usuarioId);

    List<Pedido> findByStaffId(UUID staffId);

    Pedido save(Pedido pedido);
}

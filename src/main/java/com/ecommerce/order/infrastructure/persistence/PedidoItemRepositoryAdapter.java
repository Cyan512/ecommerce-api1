package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.PedidoItem;
import com.ecommerce.order.domain.port.PedidoItemRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PedidoItemRepositoryAdapter implements PedidoItemRepositoryPort {

    private final PedidoItemJpaRepository jpaRepository;

    public PedidoItemRepositoryAdapter(PedidoItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<PedidoItem> findByPedidoId(UUID pedidoId) {
        return jpaRepository.findByPedidoId(pedidoId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public PedidoItem save(PedidoItem item) {
        return toDomain(jpaRepository.save(toEntity(item)));
    }

    @Override
    public Optional<PedidoItem> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private PedidoItem toDomain(PedidoItemEntity entity) {
        return new PedidoItem(
                entity.getId(), entity.getPedidoId(), entity.getProductoId(),
                entity.getCantidad(), entity.getPrecioUnitario(), entity.getSubtotal()
        );
    }

    private PedidoItemEntity toEntity(PedidoItem item) {
        PedidoItemEntity entity = new PedidoItemEntity();
        entity.setId(item.getId());
        entity.setPedidoId(item.getPedidoId());
        entity.setProductoId(item.getProductoId());
        entity.setCantidad(item.getCantidad());
        entity.setPrecioUnitario(item.getPrecioUnitario());
        entity.setSubtotal(item.getSubtotal());
        return entity;
    }
}

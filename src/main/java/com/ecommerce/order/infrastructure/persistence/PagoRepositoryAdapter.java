package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.Pago;
import com.ecommerce.order.domain.port.PagoRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PagoRepositoryAdapter implements PagoRepositoryPort {

    private final PagoJpaRepository jpaRepository;

    public PagoRepositoryAdapter(PagoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Pago save(Pago pago) {
        return toDomain(jpaRepository.save(toEntity(pago)));
    }

    @Override
    public Optional<Pago> findByPedidoId(UUID pedidoId) {
        return jpaRepository.findByPedidoId(pedidoId).map(this::toDomain);
    }

    @Override
    public Optional<Pago> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private Pago toDomain(PagoEntity entity) {
        return new Pago(
                entity.getId(), entity.getPedidoId(), entity.getMonto(),
                entity.getMetodoPago(), entity.getTransaccionId(),
                entity.getEstado(), entity.getFecha()
        );
    }

    private PagoEntity toEntity(Pago p) {
        PagoEntity entity = new PagoEntity();
        entity.setId(p.getId());
        entity.setPedidoId(p.getPedidoId());
        entity.setMonto(p.getMonto());
        entity.setMetodoPago(p.getMetodoPago());
        entity.setTransaccionId(p.getTransaccionId());
        entity.setEstado(p.getEstado());
        entity.setFecha(p.getFecha());
        return entity;
    }
}

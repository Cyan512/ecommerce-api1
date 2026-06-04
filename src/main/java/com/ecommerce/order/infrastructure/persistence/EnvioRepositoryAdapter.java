package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.Envio;
import com.ecommerce.order.domain.port.EnvioRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class EnvioRepositoryAdapter implements EnvioRepositoryPort {

    private final EnvioJpaRepository jpaRepository;

    public EnvioRepositoryAdapter(EnvioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Envio save(Envio envio) {
        return toDomain(jpaRepository.save(toEntity(envio)));
    }

    @Override
    public Optional<Envio> findByPedidoId(UUID pedidoId) {
        return jpaRepository.findByPedidoId(pedidoId).map(this::toDomain);
    }

    @Override
    public Optional<Envio> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    private Envio toDomain(EnvioEntity entity) {
        return new Envio(
                entity.getId(), entity.getPedidoId(), entity.getDireccion(),
                entity.getTransportista(), entity.getNumeroGuia(), entity.getEstado(),
                entity.getFechaEnvio(), entity.getFechaEntrega()
        );
    }

    private EnvioEntity toEntity(Envio e) {
        EnvioEntity entity = new EnvioEntity();
        entity.setId(e.getId());
        entity.setPedidoId(e.getPedidoId());
        entity.setDireccion(e.getDireccion());
        entity.setTransportista(e.getTransportista());
        entity.setNumeroGuia(e.getNumeroGuia());
        entity.setEstado(e.getEstado());
        entity.setFechaEnvio(e.getFechaEnvio());
        entity.setFechaEntrega(e.getFechaEntrega());
        return entity;
    }
}

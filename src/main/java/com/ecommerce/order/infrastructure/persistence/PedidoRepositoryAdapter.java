package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.Pedido;
import com.ecommerce.order.domain.port.PedidoRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class PedidoRepositoryAdapter implements PedidoRepositoryPort {

    private final PedidoJpaRepository jpaRepository;

    public PedidoRepositoryAdapter(PedidoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Pedido> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Pedido> findByStaffId(UUID staffId) {
        return jpaRepository.findByStaffId(staffId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Pedido save(Pedido pedido) {
        return toDomain(jpaRepository.save(toEntity(pedido)));
    }

    private Pedido toDomain(PedidoEntity entity) {
        return new Pedido(
                entity.getId(), entity.getUsuarioId(), entity.getDireccionId(),
                entity.getStaffId(), entity.getCuponId(), entity.getSubtotal(),
                entity.getDescuento(), entity.getTotal(), entity.getEstado(),
                entity.getFechaCreacion(), entity.getFechaActualizacion()
        );
    }

    private PedidoEntity toEntity(Pedido p) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(p.getId());
        entity.setUsuarioId(p.getUsuarioId());
        entity.setDireccionId(p.getDireccionId());
        entity.setStaffId(p.getStaffId());
        entity.setCuponId(p.getCuponId());
        entity.setSubtotal(p.getSubtotal());
        entity.setDescuento(p.getDescuento());
        entity.setTotal(p.getTotal());
        entity.setEstado(p.getEstado());
        entity.setFechaCreacion(p.getFechaCreacion());
        entity.setFechaActualizacion(p.getFechaActualizacion());
        return entity;
    }
}

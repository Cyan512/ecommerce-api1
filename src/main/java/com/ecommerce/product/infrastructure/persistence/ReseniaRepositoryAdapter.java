package com.ecommerce.product.infrastructure.persistence;

import com.ecommerce.product.domain.model.Resenia;
import com.ecommerce.product.domain.port.ReseniaRepositoryPort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ReseniaRepositoryAdapter implements ReseniaRepositoryPort {

    private final ReseniaJpaRepository jpaRepository;

    public ReseniaRepositoryAdapter(ReseniaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Resenia save(Resenia resenia) {
        return toDomain(jpaRepository.save(toEntity(resenia)));
    }

    @Override
    public Optional<Resenia> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Resenia> findByProductoId(UUID productoId) {
        return jpaRepository.findByProductoId(productoId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Resenia> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Resenia> findByUsuarioIdAndProductoId(UUID usuarioId, UUID productoId) {
        return jpaRepository.findByUsuarioIdAndProductoId(usuarioId, productoId).map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Resenia toDomain(ReseniaEntity entity) {
        return new Resenia(
                entity.getId(), entity.getUsuarioId(), entity.getProductoId(),
                entity.getCalificacion(), entity.getComentario(), entity.getFecha()
        );
    }

    private ReseniaEntity toEntity(Resenia r) {
        ReseniaEntity entity = new ReseniaEntity();
        entity.setId(r.getId());
        entity.setUsuarioId(r.getUsuarioId());
        entity.setProductoId(r.getProductoId());
        entity.setCalificacion(r.getCalificacion());
        entity.setComentario(r.getComentario());
        entity.setFecha(r.getFecha() != null ? r.getFecha() : LocalDateTime.now());
        return entity;
    }
}

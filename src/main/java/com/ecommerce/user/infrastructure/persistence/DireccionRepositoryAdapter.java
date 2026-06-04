package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.Direccion;
import com.ecommerce.user.domain.port.DireccionRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class DireccionRepositoryAdapter implements DireccionRepositoryPort {

    private final DireccionJpaRepository jpaRepository;

    public DireccionRepositoryAdapter(DireccionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Direccion save(Direccion direccion) {
        return toDomain(jpaRepository.save(toEntity(direccion)));
    }

    @Override
    public Optional<Direccion> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Direccion> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Direccion toDomain(DireccionEntity entity) {
        Direccion d = new Direccion();
        d.setId(entity.getId());
        d.setUsuarioId(entity.getUsuarioId());
        d.setCalle(entity.getCalle());
        d.setColonia(entity.getColonia());
        d.setCiudad(entity.getCiudad());
        d.setEstado(entity.getEstado());
        d.setCodigoPostal(entity.getCodigoPostal());
        d.setPais(entity.getPais());
        d.setPrincipal(entity.isPrincipal());
        return d;
    }

    private DireccionEntity toEntity(Direccion d) {
        DireccionEntity entity = new DireccionEntity();
        entity.setId(d.getId());
        entity.setUsuarioId(d.getUsuarioId());
        entity.setCalle(d.getCalle());
        entity.setColonia(d.getColonia());
        entity.setCiudad(d.getCiudad());
        entity.setEstado(d.getEstado());
        entity.setCodigoPostal(d.getCodigoPostal());
        entity.setPais(d.getPais());
        entity.setPrincipal(d.isPrincipal());
        return entity;
    }
}

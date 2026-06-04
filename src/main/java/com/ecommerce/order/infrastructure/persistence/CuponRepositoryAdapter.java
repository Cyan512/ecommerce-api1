package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.Cupon;
import com.ecommerce.order.domain.port.CuponRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CuponRepositoryAdapter implements CuponRepositoryPort {

    private final CuponJpaRepository jpaRepository;

    public CuponRepositoryAdapter(CuponJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Cupon> findByCodigo(String codigo) {
        return jpaRepository.findByCodigo(codigo).map(this::toDomain);
    }

    @Override
    public Cupon save(Cupon cupon) {
        return toDomain(jpaRepository.save(toEntity(cupon)));
    }

    @Override
    public List<Cupon> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    private Cupon toDomain(CuponEntity entity) {
        return new Cupon(
                entity.getId(), entity.getCodigo(), entity.getTipoDescuento(),
                entity.getValorDescuento(), entity.getMontoMinimo(), entity.getUsosMaximos(),
                entity.getUsosActuales(), entity.getFechaExpiracion(), entity.isActivo()
        );
    }

    private CuponEntity toEntity(Cupon c) {
        CuponEntity entity = new CuponEntity();
        entity.setId(c.getId());
        entity.setCodigo(c.getCodigo());
        entity.setTipoDescuento(c.getTipoDescuento());
        entity.setValorDescuento(c.getValorDescuento());
        entity.setMontoMinimo(c.getMontoMinimo());
        entity.setUsosMaximos(c.getUsosMaximos());
        entity.setUsosActuales(c.getUsosActuales());
        entity.setFechaExpiracion(c.getFechaExpiracion());
        entity.setActivo(c.isActivo());
        return entity;
    }
}

package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.cart.domain.model.Carrito;
import com.ecommerce.cart.domain.port.CarritoRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CarritoRepositoryAdapter implements CarritoRepositoryPort {

    private final CarritoJpaRepository jpaRepository;

    public CarritoRepositoryAdapter(CarritoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Carrito> findByUsuarioId(UUID usuarioId) {
        return jpaRepository.findByUsuarioId(usuarioId).map(this::toDomain);
    }

    @Override
    public Carrito save(Carrito carrito) {
        return toDomain(jpaRepository.save(toEntity(carrito)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Carrito toDomain(CarritoEntity entity) {
        return new Carrito(entity.getId(), entity.getUsuarioId());
    }

    private CarritoEntity toEntity(Carrito c) {
        CarritoEntity entity = new CarritoEntity();
        entity.setId(c.getId());
        entity.setUsuarioId(c.getUsuarioId());
        return entity;
    }
}

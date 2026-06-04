package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.cart.domain.model.CarritoItem;
import com.ecommerce.cart.domain.port.CarritoItemRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CarritoItemRepositoryAdapter implements CarritoItemRepositoryPort {

    private final CarritoItemJpaRepository jpaRepository;

    public CarritoItemRepositoryAdapter(CarritoItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CarritoItem> findByCarritoId(UUID carritoId) {
        return jpaRepository.findByCarritoId(carritoId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<CarritoItem> findByCarritoIdAndProductoId(UUID carritoId, UUID productoId) {
        return jpaRepository.findByCarritoIdAndProductoId(carritoId, productoId).map(this::toDomain);
    }

    @Override
    public CarritoItem save(CarritoItem item) {
        return toDomain(jpaRepository.save(toEntity(item)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByCarritoId(UUID carritoId) {
        jpaRepository.deleteByCarritoId(carritoId);
    }

    private CarritoItem toDomain(CarritoItemEntity entity) {
        return new CarritoItem(entity.getId(), entity.getCarritoId(), entity.getProductoId(), entity.getCantidad());
    }

    private CarritoItemEntity toEntity(CarritoItem item) {
        CarritoItemEntity entity = new CarritoItemEntity();
        entity.setId(item.getId());
        entity.setCarritoId(item.getCarritoId());
        entity.setProductoId(item.getProductoId());
        entity.setCantidad(item.getCantidad());
        return entity;
    }
}

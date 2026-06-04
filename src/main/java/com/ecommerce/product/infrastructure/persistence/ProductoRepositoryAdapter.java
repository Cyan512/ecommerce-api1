package com.ecommerce.product.infrastructure.persistence;

import com.ecommerce.product.domain.model.Producto;
import com.ecommerce.product.domain.port.ProductoRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoJpaRepository jpaRepository;

    public ProductoRepositoryAdapter(ProductoJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Producto> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Producto> findByCategoriaId(UUID categoriaId) {
        return jpaRepository.findByCategoriaId(categoriaId).stream()
                .map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Producto save(Producto producto) {
        return toDomain(jpaRepository.save(toEntity(producto)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Producto toDomain(ProductoEntity entity) {
        return new Producto(
                entity.getId(), entity.getNombre(), entity.getDescripcion(),
                entity.getPrecio(), entity.getStock(), entity.getCategoriaId(),
                entity.getImagenUrl(), entity.isActivo()
        );
    }

    private ProductoEntity toEntity(Producto p) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(p.getId());
        entity.setNombre(p.getNombre());
        entity.setDescripcion(p.getDescripcion());
        entity.setPrecio(p.getPrecio());
        entity.setStock(p.getStock());
        entity.setCategoriaId(p.getCategoriaId());
        entity.setImagenUrl(p.getImagenUrl());
        entity.setActivo(p.isActivo());
        return entity;
    }
}

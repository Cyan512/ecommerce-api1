package com.ecommerce.category.infrastructure.persistence;

import com.ecommerce.category.domain.model.Categoria;
import com.ecommerce.category.domain.port.CategoriaRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CategoriaRepositoryAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository jpaRepository;

    public CategoriaRepositoryAdapter(CategoriaJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return toDomain(jpaRepository.save(toEntity(categoria)));
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private Categoria toDomain(CategoriaEntity entity) {
        return new Categoria(entity.getId(), entity.getNombre(), entity.getDescripcion(), entity.getPadreId());
    }

    private CategoriaEntity toEntity(Categoria c) {
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(c.getId());
        entity.setNombre(c.getNombre());
        entity.setDescripcion(c.getDescripcion());
        entity.setPadreId(c.getPadreId());
        return entity;
    }
}

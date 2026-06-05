package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.*;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserJpaRepository jpaRepository;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(this::toDomain);
    }

    @Override
    public Optional<User> findByOauthProviderAndOauthId(String oauthProvider, String oauthId) {
        return jpaRepository.findByOauthProviderAndOauthId(oauthProvider, oauthId).map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        return jpaRepository.save(entity).toDomain();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }

    private User toDomain(UserEntity entity) {
        return entity.toDomain();
    }

    private UserEntity toEntity(User user) {
        if (user instanceof Cliente) {
            ClienteEntity entity = new ClienteEntity();
            entity.fromDomain(user);
            return entity;
        }
        if (user instanceof Administrador) {
            AdministradorEntity entity = new AdministradorEntity();
            entity.fromDomain(user);
            return entity;
        }
        if (user instanceof Vendedor) {
            VendedorEntity entity = new VendedorEntity();
            entity.fromDomain(user);
            return entity;
        }
        throw new IllegalArgumentException("Tipo de usuario desconocido: " + user.getClass());
    }
}

package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.AuthProvider;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId) {
        return jpaRepository.findByProviderAndProviderId(provider, providerId).map(this::toDomain);
    }

    @Override
    public User save(User user) {
        UserEntity entity = toEntity(user);
        return toDomain(jpaRepository.save(entity));
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    private User toDomain(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        user.setName(entity.getName());
        user.setProvider(entity.getProvider());
        user.setProviderId(entity.getProviderId());
        user.setRoles(entity.getRoles());
        user.setEnabled(entity.isEnabled());
        return user;
    }

    private UserEntity toEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setName(user.getName());
        entity.setProvider(user.getProvider());
        entity.setProviderId(user.getProviderId());
        entity.setRoles(user.getRoles());
        entity.setEnabled(user.isEnabled());
        return entity;
    }
}

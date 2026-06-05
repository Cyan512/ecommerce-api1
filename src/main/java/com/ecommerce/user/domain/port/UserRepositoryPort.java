package com.ecommerce.user.domain.port;

import com.ecommerce.user.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    Optional<User> findByOauthProviderAndOauthId(String oauthProvider, String oauthId);

    User save(User user);

    boolean existsByEmail(String email);

    Optional<User> findById(UUID id);

    List<User> findAll();

    void deleteById(UUID id);
}

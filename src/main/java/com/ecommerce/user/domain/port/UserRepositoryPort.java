package com.ecommerce.user.domain.port;

import com.ecommerce.user.domain.model.AuthProvider;
import com.ecommerce.user.domain.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<User> findByEmail(String email);

    Optional<User> findByProviderAndProviderId(AuthProvider provider, String providerId);

    User save(User user);

    boolean existsByEmail(String email);
}

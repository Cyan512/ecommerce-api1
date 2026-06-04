package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import com.ecommerce.auth.domain.port.TokenPort;
import com.ecommerce.user.domain.model.AuthProvider;
import com.ecommerce.user.domain.model.Role;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GoogleLoginUseCase implements AuthInputPort {

    private final UserRepositoryPort userRepository;
    private final TokenPort tokenPort;

    public GoogleLoginUseCase(UserRepositoryPort userRepository, TokenPort tokenPort) {
        this.userRepository = userRepository;
        this.tokenPort = tokenPort;
    }

    @Override
    public AuthUser login(String email, String password) {
        throw new UnsupportedOperationException("Usar LocalLoginUseCase para login local");
    }

    @Override
    public AuthUser register(String email, String password, String name) {
        throw new UnsupportedOperationException("Usar LocalLoginUseCase para registro local");
    }

    @Override
    public AuthUser loginWithGoogle(String email, String name, String googleId) {
        User user = userRepository.findByProviderAndProviderId(AuthProvider.GOOGLE, googleId)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setProvider(AuthProvider.GOOGLE);
                    newUser.setProviderId(googleId);
                    newUser.setRoles(Set.of(Role.ROLE_USER));
                    newUser.setEnabled(true);
                    return userRepository.save(newUser);
                });

        if (!user.isEnabled()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        String token = tokenPort.generateToken(user);
        return new AuthUser(user.getId(), user.getEmail(), user.getName(), user.getRoles(), token);
    }
}

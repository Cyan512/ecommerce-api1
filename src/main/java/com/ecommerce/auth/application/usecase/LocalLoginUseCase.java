package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import com.ecommerce.auth.domain.port.TokenPort;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LocalLoginUseCase implements AuthInputPort {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenPort tokenPort;

    public LocalLoginUseCase(UserRepositoryPort userRepository,
                             PasswordEncoder passwordEncoder,
                             TokenPort tokenPort) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenPort = tokenPort;
    }

    @Override
    public AuthUser login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!user.isEnabled()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        String token = tokenPort.generateToken(user);
        return new AuthUser(user.getId(), user.getEmail(), user.getName(), user.getRoles(), token);
    }

    @Override
    public AuthUser register(String email, String password, String name) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El email ya está registrado");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setName(name);
        user.setProvider(com.ecommerce.user.domain.model.AuthProvider.LOCAL);
        user.setRoles(java.util.Set.of(com.ecommerce.user.domain.model.Role.ROLE_USER));
        user.setEnabled(true);

        User saved = userRepository.save(user);
        String token = tokenPort.generateToken(saved);
        return new AuthUser(saved.getId(), saved.getEmail(), saved.getName(), saved.getRoles(), token);
    }

    @Override
    public AuthUser loginWithGoogle(String email, String name, String googleId) {
        throw new UnsupportedOperationException("Usar GoogleLoginUseCase para login con Google");
    }
}

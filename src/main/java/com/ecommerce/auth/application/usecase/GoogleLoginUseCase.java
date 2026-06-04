package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import com.ecommerce.auth.domain.port.TokenPort;
import com.ecommerce.user.domain.model.Cliente;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    public AuthUser register(String email, String password, String nombre) {
        throw new UnsupportedOperationException("Usar LocalLoginUseCase para registro local");
    }

    @Override
    public AuthUser loginWithGoogle(String email, String nombre, String googleId) {
        User user = userRepository.findByOauthProviderAndOauthId("google", googleId)
                .orElseGet(() -> {
                    Cliente nuevo = new Cliente();
                    nuevo.setEmail(email);
                    nuevo.setNombre(nombre);
                    nuevo.setOauthProvider("google");
                    nuevo.setOauthId(googleId);
                    nuevo.setFechaRegistro(LocalDateTime.now());
                    nuevo.setActivo(true);
                    return userRepository.save(nuevo);
                });

        if (!user.isActivo()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        String token = tokenPort.generateToken(user);
        return new AuthUser(user.getId(), user.getEmail(), user.getNombre(), user.getTipo(), token);
    }
}

package com.ecommerce.auth.application.usecase;

import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import com.ecommerce.auth.domain.port.TokenPort;
import com.ecommerce.exception.BusinessException;
import com.ecommerce.user.domain.model.Cliente;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        if (user.getPassword() == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        if (!user.isActivo()) {
            throw new RuntimeException("Usuario deshabilitado");
        }

        String token = tokenPort.generateToken(user);
        return new AuthUser(user.getId(), user.getEmail(), user.getNombre(), user.getTipo(), token);
    }

    @Override
    public AuthUser register(String email, String password, String nombre) {
        if (userRepository.existsByEmail(email)) {
            throw new BusinessException("El email ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setEmail(email);
        cliente.setPassword(passwordEncoder.encode(password));
        cliente.setNombre(nombre);
        cliente.setFechaRegistro(LocalDateTime.now());
        cliente.setActivo(true);

        User saved = userRepository.save(cliente);
        String token = tokenPort.generateToken(saved);
        return new AuthUser(saved.getId(), saved.getEmail(), saved.getNombre(), saved.getTipo(), token);
    }

    @Override
    public AuthUser loginWithGoogle(String email, String nombre, String googleId) {
        throw new UnsupportedOperationException("Usar GoogleLoginUseCase para login con Google");
    }
}

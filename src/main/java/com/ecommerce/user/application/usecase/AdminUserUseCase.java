package com.ecommerce.user.application.usecase;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.user.application.dto.AdminUserRequest;
import com.ecommerce.user.application.dto.AdminUserResponse;
import com.ecommerce.user.domain.model.Administrador;
import com.ecommerce.user.domain.model.Cliente;
import com.ecommerce.user.domain.model.TipoUsuario;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.model.Vendedor;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdminUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminUserUseCase(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AdminUserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AdminUserResponse create(AdminUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BusinessException("El email ya está registrado");
        }

        TipoUsuario tipo;
        try {
            tipo = TipoUsuario.valueOf(request.getTipo().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Tipo de usuario inválido: " + request.getTipo());
        }

        User user = switch (tipo) {
            case CLIENTE -> new Cliente();
            case ADMINISTRADOR -> new Administrador();
            case VENDEDOR -> new Vendedor();
        };

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setNombre(request.getNombre());
        user.setFechaRegistro(LocalDateTime.now());
        user.setActivo(true);

        User saved = userRepository.save(user);
        return toResponse(saved);
    }

    public AdminUserResponse block(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        user.setActivo(false);
        return toResponse(userRepository.save(user));
    }

    public AdminUserResponse unblock(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        user.setActivo(true);
        return toResponse(userRepository.save(user));
    }

    public void delete(UUID id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Usuario", id);
        }
        userRepository.deleteById(id);
    }

    private AdminUserResponse toResponse(User user) {
        return new AdminUserResponse(
                user.getId(), user.getEmail(), user.getNombre(),
                user.getTipo().name(), user.isActivo()
        );
    }
}

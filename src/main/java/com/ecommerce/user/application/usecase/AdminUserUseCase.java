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
import com.ecommerce.order.domain.port.PedidoRepositoryPort;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.beans.factory.annotation.Value;
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
    private final String defaultAdminEmail;
    private final PedidoRepositoryPort pedidoRepository;

    public AdminUserUseCase(UserRepositoryPort userRepository,
                            PasswordEncoder passwordEncoder,
                            PedidoRepositoryPort pedidoRepository,
                            @Value("${app.default-admin.email}") String defaultAdminEmail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.pedidoRepository = pedidoRepository;
        this.defaultAdminEmail = defaultAdminEmail;
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", id));
        if (user.getEmail().equals(defaultAdminEmail)) {
            throw new BusinessException("No se puede eliminar la cuenta administradora por defecto");
        }
        if (!pedidoRepository.findByUsuarioId(id).isEmpty()) {
            throw new BusinessException("No se puede eliminar el usuario porque tiene pedidos asociados. Bloquéelo en su lugar.");
        }
        userRepository.deleteById(id);
    }

    public void changePassword(UUID userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", userId));
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BusinessException("La contraseña actual no es correcta");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public AdminUserResponse changeNombre(UUID userId, String nuevoNombre) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", userId));
        user.setNombre(nuevoNombre);
        return toResponse(userRepository.save(user));
    }

    public AdminUserResponse profile(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", userId));
        return toResponse(user);
    }

    private AdminUserResponse toResponse(User user) {
        return new AdminUserResponse(
                user.getId(), user.getEmail(), user.getNombre(),
                user.getTipo().name(), user.isActivo()
        );
    }
}

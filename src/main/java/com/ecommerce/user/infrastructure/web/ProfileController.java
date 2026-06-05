package com.ecommerce.user.infrastructure.web;

import com.ecommerce.user.application.dto.AdminUserResponse;
import com.ecommerce.user.application.usecase.AdminUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final AdminUserUseCase adminUserUseCase;

    public ProfileController(AdminUserUseCase adminUserUseCase) {
        this.adminUserUseCase = adminUserUseCase;
    }

    @GetMapping
    public ResponseEntity<AdminUserResponse> profile(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(adminUserUseCase.profile(userId));
    }

    @PutMapping("/password")
    public ResponseEntity<Map<String, String>> changePassword(Authentication auth,
                                                              @RequestBody Map<String, String> body) {
        UUID userId = (UUID) auth.getPrincipal();
        String currentPassword = body.get("currentPassword");
        String newPassword = body.get("newPassword");
        adminUserUseCase.changePassword(userId, currentPassword, newPassword);
        return ResponseEntity.ok(Map.of("message", "Contraseña actualizada correctamente"));
    }

    @PutMapping("/nombre")
    public ResponseEntity<AdminUserResponse> changeNombre(Authentication auth,
                                                          @RequestBody Map<String, String> body) {
        UUID userId = (UUID) auth.getPrincipal();
        String nuevoNombre = body.get("nombre");
        return ResponseEntity.ok(adminUserUseCase.changeNombre(userId, nuevoNombre));
    }
}

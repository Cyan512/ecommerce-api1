package com.ecommerce.user.infrastructure.web;

import com.ecommerce.user.application.dto.DireccionRequest;
import com.ecommerce.user.application.dto.DireccionResponse;
import com.ecommerce.user.application.usecase.DireccionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionUseCase direccionUseCase;

    public DireccionController(DireccionUseCase direccionUseCase) {
        this.direccionUseCase = direccionUseCase;
    }

    @GetMapping
    public ResponseEntity<List<DireccionResponse>> myDirecciones(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(direccionUseCase.findByUsuario(userId));
    }

    @PostMapping
    public ResponseEntity<DireccionResponse> create(Authentication auth,
                                                    @Valid @RequestBody DireccionRequest request) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(direccionUseCase.create(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable UUID id) {
        UUID userId = (UUID) auth.getPrincipal();
        direccionUseCase.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}

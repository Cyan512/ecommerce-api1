package com.ecommerce.product.infrastructure.web;

import com.ecommerce.product.application.dto.ReseniaRequest;
import com.ecommerce.product.application.dto.ReseniaResponse;
import com.ecommerce.product.application.usecase.ReseniaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReseniaController {

    private final ReseniaUseCase reseniaUseCase;

    public ReseniaController(ReseniaUseCase reseniaUseCase) {
        this.reseniaUseCase = reseniaUseCase;
    }

    @GetMapping("/product/{productoId}")
    public ResponseEntity<List<ReseniaResponse>> findByProducto(@PathVariable UUID productoId) {
        return ResponseEntity.ok(reseniaUseCase.findByProducto(productoId));
    }

    @PostMapping
    public ResponseEntity<ReseniaResponse> create(Authentication auth,
                                                  @Valid @RequestBody ReseniaRequest request) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reseniaUseCase.create(userId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Authentication auth, @PathVariable UUID id) {
        UUID userId = (UUID) auth.getPrincipal();
        reseniaUseCase.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}

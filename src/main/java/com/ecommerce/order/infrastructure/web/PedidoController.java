package com.ecommerce.order.infrastructure.web;

import com.ecommerce.order.application.dto.PedidoRequest;
import com.ecommerce.order.application.dto.PedidoResponse;
import com.ecommerce.order.application.usecase.PedidoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class PedidoController {

    private final PedidoUseCase pedidoUseCase;

    public PedidoController(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> myOrders(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(pedidoUseCase.findByUsuario(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(pedidoUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> create(Authentication auth,
                                                 @Valid @RequestBody PedidoRequest request) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pedidoUseCase.create(userId, request));
    }
}

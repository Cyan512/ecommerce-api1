package com.ecommerce.cart.infrastructure.web;

import com.ecommerce.cart.application.dto.CarritoItemRequest;
import com.ecommerce.cart.application.dto.CarritoResponse;
import com.ecommerce.cart.application.usecase.CarritoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
public class CarritoController {

    private final CarritoUseCase carritoUseCase;

    public CarritoController(CarritoUseCase carritoUseCase) {
        this.carritoUseCase = carritoUseCase;
    }

    @GetMapping
    public ResponseEntity<CarritoResponse> getCarrito(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(carritoUseCase.getCarrito(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<CarritoResponse> addItem(Authentication auth,
                                                   @Valid @RequestBody CarritoItemRequest request) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(carritoUseCase.addItem(userId, request));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(Authentication auth, @PathVariable UUID itemId) {
        UUID userId = (UUID) auth.getPrincipal();
        carritoUseCase.removeItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearCart(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        carritoUseCase.clearCarrito(userId);
        return ResponseEntity.noContent().build();
    }
}

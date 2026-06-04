package com.ecommerce.product.infrastructure.web;

import com.ecommerce.product.application.dto.ProductoRequest;
import com.ecommerce.product.application.dto.ProductoResponse;
import com.ecommerce.product.application.usecase.ProductoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductoController {

    private final ProductoUseCase productoUseCase;

    public AdminProductoController(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> create(@Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoUseCase.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> update(@PathVariable UUID id,
                                                   @Valid @RequestBody ProductoRequest request) {
        return ResponseEntity.ok(productoUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productoUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

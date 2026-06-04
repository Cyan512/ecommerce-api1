package com.ecommerce.product.infrastructure.web;

import com.ecommerce.product.application.dto.ProductoRequest;
import com.ecommerce.product.application.dto.ProductoResponse;
import com.ecommerce.product.application.usecase.ProductoUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductoController {

    private final ProductoUseCase productoUseCase;

    public ProductoController(ProductoUseCase productoUseCase) {
        this.productoUseCase = productoUseCase;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductoResponse>> findAll() {
        return ResponseEntity.ok(productoUseCase.findAll());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductoResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productoUseCase.findById(id));
    }

    @GetMapping("/products/by-category/{categoriaId}")
    public ResponseEntity<List<ProductoResponse>> findByCategoria(@PathVariable UUID categoriaId) {
        return ResponseEntity.ok(productoUseCase.findByCategoria(categoriaId));
    }
}

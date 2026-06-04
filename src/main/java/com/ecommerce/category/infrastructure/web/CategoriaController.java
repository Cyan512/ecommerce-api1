package com.ecommerce.category.infrastructure.web;

import com.ecommerce.category.application.dto.CategoriaRequest;
import com.ecommerce.category.application.dto.CategoriaResponse;
import com.ecommerce.category.application.usecase.CategoriaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    public CategoriaController(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> findAll() {
        return ResponseEntity.ok(categoriaUseCase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(categoriaUseCase.findById(id));
    }
}

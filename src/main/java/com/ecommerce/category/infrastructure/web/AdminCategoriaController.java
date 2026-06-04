package com.ecommerce.category.infrastructure.web;

import com.ecommerce.category.application.dto.CategoriaRequest;
import com.ecommerce.category.application.dto.CategoriaResponse;
import com.ecommerce.category.application.usecase.CategoriaUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin/categories")
public class AdminCategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    public AdminCategoriaController(CategoriaUseCase categoriaUseCase) {
        this.categoriaUseCase = categoriaUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaUseCase.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> update(@PathVariable UUID id,
                                                    @Valid @RequestBody CategoriaRequest request) {
        return ResponseEntity.ok(categoriaUseCase.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoriaUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

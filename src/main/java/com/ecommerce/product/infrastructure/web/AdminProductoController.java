package com.ecommerce.product.infrastructure.web;

import com.ecommerce.product.application.dto.ProductoRequest;
import com.ecommerce.product.application.dto.ProductoResponse;
import com.ecommerce.product.application.usecase.ProductoUseCase;
import com.ecommerce.product.infrastructure.cloudinary.CloudinaryService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductoController {

    private final ProductoUseCase productoUseCase;
    private final CloudinaryService cloudinaryService;

    public AdminProductoController(ProductoUseCase productoUseCase,
                                   CloudinaryService cloudinaryService) {
        this.productoUseCase = productoUseCase;
        this.cloudinaryService = cloudinaryService;
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

    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductoResponse> uploadImage(@PathVariable UUID id,
                                                         @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        String imagenUrl = cloudinaryService.upload(file);
        return ResponseEntity.ok(productoUseCase.updateImage(id, imagenUrl));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productoUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

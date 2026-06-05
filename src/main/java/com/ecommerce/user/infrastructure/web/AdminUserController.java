package com.ecommerce.user.infrastructure.web;

import com.ecommerce.user.application.dto.AdminUserRequest;
import com.ecommerce.user.application.dto.AdminUserResponse;
import com.ecommerce.user.application.usecase.AdminUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserUseCase adminUserUseCase;

    public AdminUserController(AdminUserUseCase adminUserUseCase) {
        this.adminUserUseCase = adminUserUseCase;
    }

    @GetMapping
    public ResponseEntity<List<AdminUserResponse>> findAll() {
        return ResponseEntity.ok(adminUserUseCase.findAll());
    }

    @PostMapping
    public ResponseEntity<AdminUserResponse> create(@Valid @RequestBody AdminUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(adminUserUseCase.create(request));
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<AdminUserResponse> block(@PathVariable UUID id) {
        return ResponseEntity.ok(adminUserUseCase.block(id));
    }

    @PutMapping("/{id}/unblock")
    public ResponseEntity<AdminUserResponse> unblock(@PathVariable UUID id) {
        return ResponseEntity.ok(adminUserUseCase.unblock(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        adminUserUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}

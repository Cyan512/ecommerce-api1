package com.ecommerce.auth.infrastructure.web;

import com.ecommerce.auth.application.dto.AuthResponse;
import com.ecommerce.auth.application.dto.LoginRequest;
import com.ecommerce.auth.application.dto.RegisterRequest;
import com.ecommerce.auth.domain.model.AuthUser;
import com.ecommerce.auth.domain.port.AuthInputPort;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthInputPort localLoginUseCase;
    private final AuthInputPort googleLoginUseCase;

    public AuthController(@Qualifier("localLoginUseCase") AuthInputPort localLoginUseCase,
                          @Qualifier("googleLoginUseCase") AuthInputPort googleLoginUseCase) {
        this.localLoginUseCase = localLoginUseCase;
        this.googleLoginUseCase = googleLoginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthUser authUser = localLoginUseCase.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(toResponse(authUser));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthUser authUser = localLoginUseCase.register(request.getEmail(), request.getPassword(), request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(authUser));
    }

    @PostMapping("/google")
    public ResponseEntity<AuthResponse> googleLogin(@RequestParam String email,
                                                    @RequestParam String name,
                                                    @RequestParam("google_id") String googleId) {
        AuthUser authUser = googleLoginUseCase.loginWithGoogle(email, name, googleId);
        return ResponseEntity.ok(toResponse(authUser));
    }

    @GetMapping("/callback")
    public ResponseEntity<AuthResponse> oauth2Callback(@RequestParam String token) {
        return ResponseEntity.ok(new AuthResponse(token, null, null, null));
    }

    private AuthResponse toResponse(AuthUser authUser) {
        return new AuthResponse(
                authUser.getToken(),
                authUser.getEmail(),
                authUser.getName(),
                authUser.getRoles().stream().map(Enum::name).collect(Collectors.toList())
        );
    }
}

package com.ecommerce.cart.infrastructure.web;

import com.ecommerce.cart.application.dto.WishlistResponse;
import com.ecommerce.cart.application.usecase.WishlistUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistUseCase wishlistUseCase;

    public WishlistController(WishlistUseCase wishlistUseCase) {
        this.wishlistUseCase = wishlistUseCase;
    }

    @GetMapping
    public ResponseEntity<WishlistResponse> getWishlist(Authentication auth) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(wishlistUseCase.getWishlist(userId));
    }

    @PostMapping("/items")
    public ResponseEntity<WishlistResponse> addItem(Authentication auth,
                                                    @RequestBody Map<String, UUID> body) {
        UUID userId = (UUID) auth.getPrincipal();
        return ResponseEntity.ok(wishlistUseCase.addItem(userId, body.get("productoId")));
    }

    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<Void> removeItem(Authentication auth, @PathVariable UUID itemId) {
        UUID userId = (UUID) auth.getPrincipal();
        wishlistUseCase.removeItem(userId, itemId);
        return ResponseEntity.noContent().build();
    }
}

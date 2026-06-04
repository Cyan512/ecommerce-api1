package com.ecommerce.auth.domain.port;

import com.ecommerce.user.domain.model.User;

import java.util.UUID;

public interface TokenPort {

    String generateToken(User user);

    UUID getUserIdFromToken(String token);

    boolean isTokenValid(String token);
}

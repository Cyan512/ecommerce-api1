package com.ecommerce.auth.domain.port;

import com.ecommerce.user.domain.model.User;

public interface TokenPort {

    String generateToken(User user);

    Long getUserIdFromToken(String token);

    boolean isTokenValid(String token);
}

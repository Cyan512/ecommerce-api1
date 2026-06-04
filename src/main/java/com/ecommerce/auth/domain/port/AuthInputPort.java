package com.ecommerce.auth.domain.port;

import com.ecommerce.auth.domain.model.AuthUser;

public interface AuthInputPort {

    AuthUser login(String email, String password);

    AuthUser register(String email, String password, String name);

    AuthUser loginWithGoogle(String email, String name, String googleId);
}

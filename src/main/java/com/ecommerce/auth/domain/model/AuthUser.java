package com.ecommerce.auth.domain.model;

import com.ecommerce.user.domain.model.Role;

import java.util.Set;

public class AuthUser {

    private final Long id;
    private final String email;
    private final String name;
    private final Set<Role> roles;
    private final String token;

    public AuthUser(Long id, String email, String name, Set<Role> roles, String token) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.roles = roles;
        this.token = token;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public Set<Role> getRoles() { return roles; }
    public String getToken() { return token; }
}

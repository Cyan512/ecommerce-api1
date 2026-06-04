package com.ecommerce.auth.domain.model;

import com.ecommerce.user.domain.model.TipoUsuario;

import java.util.UUID;

public class AuthUser {

    private final UUID id;
    private final String email;
    private final String nombre;
    private final TipoUsuario tipo;
    private final String token;

    public AuthUser(UUID id, String email, String nombre, TipoUsuario tipo, String token) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.tipo = tipo;
        this.token = token;
    }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getNombre() { return nombre; }
    public TipoUsuario getTipo() { return tipo; }
    public String getToken() { return token; }
}

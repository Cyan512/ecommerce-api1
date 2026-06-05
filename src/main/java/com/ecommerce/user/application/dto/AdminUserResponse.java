package com.ecommerce.user.application.dto;

import java.util.UUID;

public class AdminUserResponse {

    private UUID id;
    private String email;
    private String nombre;
    private String tipo;
    private boolean activo;

    public AdminUserResponse() {}

    public AdminUserResponse(UUID id, String email, String nombre, String tipo, boolean activo) {
        this.id = id;
        this.email = email;
        this.nombre = nombre;
        this.tipo = tipo;
        this.activo = activo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

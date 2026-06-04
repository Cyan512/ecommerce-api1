package com.ecommerce.auth.application.dto;

public class AuthResponse {

    private String token;
    private String email;
    private String nombre;
    private String tipo;

    public AuthResponse() {}

    public AuthResponse(String token, String email, String nombre, String tipo) {
        this.token = token;
        this.email = email;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}

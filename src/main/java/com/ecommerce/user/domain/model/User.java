package com.ecommerce.user.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class User {

    private UUID id;
    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private String oauthProvider;
    private String oauthId;
    private TipoUsuario tipo;
    private LocalDateTime fechaRegistro;
    private boolean activo;

    protected User() {}

    protected User(UUID id, String nombre, String email, String password, String telefono,
                   String oauthProvider, String oauthId, TipoUsuario tipo,
                   LocalDateTime fechaRegistro, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.oauthProvider = oauthProvider;
        this.oauthId = oauthId;
        this.tipo = tipo;
        this.fechaRegistro = fechaRegistro;
        this.activo = activo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getOauthProvider() { return oauthProvider; }
    public void setOauthProvider(String oauthProvider) { this.oauthProvider = oauthProvider; }
    public String getOauthId() { return oauthId; }
    public void setOauthId(String oauthId) { this.oauthId = oauthId; }
    public TipoUsuario getTipo() { return tipo; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDateTime fechaRegistro) { this.fechaRegistro = fechaRegistro; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

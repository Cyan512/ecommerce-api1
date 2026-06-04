package com.ecommerce.category.application.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CategoriaRequest {

    @NotBlank
    private String nombre;

    private String descripcion;

    private UUID padreId;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public UUID getPadreId() { return padreId; }
    public void setPadreId(UUID padreId) { this.padreId = padreId; }
}

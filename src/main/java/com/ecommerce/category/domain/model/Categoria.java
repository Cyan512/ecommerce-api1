package com.ecommerce.category.domain.model;

import java.util.UUID;

public class Categoria {

    private UUID id;
    private String nombre;
    private String descripcion;
    private UUID padreId;

    public Categoria() {}

    public Categoria(UUID id, String nombre, String descripcion, UUID padreId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.padreId = padreId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public UUID getPadreId() { return padreId; }
    public void setPadreId(UUID padreId) { this.padreId = padreId; }
}

package com.ecommerce.category.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "padre_id")
    private UUID padreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "padre_id", insertable = false, updatable = false)
    private CategoriaEntity padre;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public UUID getPadreId() { return padreId; }
    public void setPadreId(UUID padreId) { this.padreId = padreId; }
    public CategoriaEntity getPadre() { return padre; }
    public void setPadre(CategoriaEntity padre) { this.padre = padre; }
}

package com.ecommerce.product.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductoResponse {

    private UUID id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private UUID categoriaId;
    private String imagenUrl;
    private boolean activo;

    public ProductoResponse() {}

    public ProductoResponse(UUID id, String nombre, String descripcion, BigDecimal precio,
                            int stock, UUID categoriaId, String imagenUrl, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoriaId = categoriaId;
        this.imagenUrl = imagenUrl;
        this.activo = activo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public UUID getCategoriaId() { return categoriaId; }
    public void setCategoriaId(UUID categoriaId) { this.categoriaId = categoriaId; }
    public String getImagenUrl() { return imagenUrl; }
    public void setImagenUrl(String imagenUrl) { this.imagenUrl = imagenUrl; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

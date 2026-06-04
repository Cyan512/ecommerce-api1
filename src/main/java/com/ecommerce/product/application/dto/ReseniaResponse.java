package com.ecommerce.product.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReseniaResponse {

    private UUID id;
    private UUID usuarioId;
    private String usuarioNombre;
    private UUID productoId;
    private int calificacion;
    private String comentario;
    private LocalDateTime fecha;

    public ReseniaResponse() {}

    public ReseniaResponse(UUID id, UUID usuarioId, String usuarioNombre, UUID productoId,
                           int calificacion, String comentario, LocalDateTime fecha) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
        this.productoId = productoId;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fecha = fecha;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public String getUsuarioNombre() { return usuarioNombre; }
    public void setUsuarioNombre(String usuarioNombre) { this.usuarioNombre = usuarioNombre; }
    public UUID getProductoId() { return productoId; }
    public void setProductoId(UUID productoId) { this.productoId = productoId; }
    public int getCalificacion() { return calificacion; }
    public void setCalificacion(int calificacion) { this.calificacion = calificacion; }
    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}

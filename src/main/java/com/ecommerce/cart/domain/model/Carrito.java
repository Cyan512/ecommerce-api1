package com.ecommerce.cart.domain.model;

import java.util.UUID;

public class Carrito {

    private UUID id;
    private UUID usuarioId;

    public Carrito() {}

    public Carrito(UUID id, UUID usuarioId) {
        this.id = id;
        this.usuarioId = usuarioId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
}

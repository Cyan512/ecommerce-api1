package com.ecommerce.cart.infrastructure.persistence;

import com.ecommerce.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "carritos", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id"})
})
public class CarritoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "usuario_id", nullable = false, unique = true)
    private UUID usuarioId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UserEntity usuario;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public UserEntity getUsuario() { return usuario; }
    public void setUsuario(UserEntity usuario) { this.usuario = usuario; }
}

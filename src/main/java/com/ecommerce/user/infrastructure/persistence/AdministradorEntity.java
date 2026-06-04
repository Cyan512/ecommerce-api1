package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.Administrador;
import com.ecommerce.user.domain.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class AdministradorEntity extends UserEntity {

    @Override
    public User toDomain() {
        return new Administrador(
                getId(), getNombre(), getEmail(), getPassword(), getTelefono(),
                getOauthProvider(), getOauthId(), getFechaRegistro(), isActivo()
        );
    }

    @Override
    public void fromDomain(User user) {
        Administrador admin = (Administrador) user;
        setId(admin.getId());
        setNombre(admin.getNombre());
        setEmail(admin.getEmail());
        setPassword(admin.getPassword());
        setTelefono(admin.getTelefono());
        setOauthProvider(admin.getOauthProvider());
        setOauthId(admin.getOauthId());
        setFechaRegistro(admin.getFechaRegistro() != null ? admin.getFechaRegistro() : LocalDateTime.now());
        setActivo(admin.isActivo());
    }
}

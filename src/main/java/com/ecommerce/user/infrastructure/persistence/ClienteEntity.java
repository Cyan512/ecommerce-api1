package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.Cliente;
import com.ecommerce.user.domain.model.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("CLIENTE")
public class ClienteEntity extends UserEntity {

    @Override
    public User toDomain() {
        return new Cliente(
                getId(), getNombre(), getEmail(), getPassword(), getTelefono(),
                getOauthProvider(), getOauthId(), getFechaRegistro(), isActivo()
        );
    }

    @Override
    public void fromDomain(User user) {
        Cliente cliente = (Cliente) user;
        setId(cliente.getId());
        setNombre(cliente.getNombre());
        setEmail(cliente.getEmail());
        setPassword(cliente.getPassword());
        setTelefono(cliente.getTelefono());
        setOauthProvider(cliente.getOauthProvider());
        setOauthId(cliente.getOauthId());
        setFechaRegistro(cliente.getFechaRegistro() != null ? cliente.getFechaRegistro() : LocalDateTime.now());
        setActivo(cliente.isActivo());
    }
}

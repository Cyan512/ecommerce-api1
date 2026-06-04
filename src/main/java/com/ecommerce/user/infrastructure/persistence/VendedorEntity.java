package com.ecommerce.user.infrastructure.persistence;

import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.model.Vendedor;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("VENDEDOR")
public class VendedorEntity extends UserEntity {

    @Override
    public User toDomain() {
        return new Vendedor(
                getId(), getNombre(), getEmail(), getPassword(), getTelefono(),
                getOauthProvider(), getOauthId(), getFechaRegistro(), isActivo()
        );
    }

    @Override
    public void fromDomain(User user) {
        Vendedor vendedor = (Vendedor) user;
        setId(vendedor.getId());
        setNombre(vendedor.getNombre());
        setEmail(vendedor.getEmail());
        setPassword(vendedor.getPassword());
        setTelefono(vendedor.getTelefono());
        setOauthProvider(vendedor.getOauthProvider());
        setOauthId(vendedor.getOauthId());
        setFechaRegistro(vendedor.getFechaRegistro() != null ? vendedor.getFechaRegistro() : LocalDateTime.now());
        setActivo(vendedor.isActivo());
    }
}

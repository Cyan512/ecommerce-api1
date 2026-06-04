package com.ecommerce.user.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cliente extends User {

    public Cliente() {
        super();
        setTipo(TipoUsuario.CLIENTE);
    }

    public Cliente(UUID id, String nombre, String email, String password, String telefono,
                   String oauthProvider, String oauthId, LocalDateTime fechaRegistro, boolean activo) {
        super(id, nombre, email, password, telefono, oauthProvider, oauthId,
              TipoUsuario.CLIENTE, fechaRegistro, activo);
    }
}

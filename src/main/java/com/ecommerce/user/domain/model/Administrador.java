package com.ecommerce.user.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Administrador extends User {

    public Administrador() {
        super();
        setTipo(TipoUsuario.ADMINISTRADOR);
    }

    public Administrador(UUID id, String nombre, String email, String password, String telefono,
                         String oauthProvider, String oauthId, LocalDateTime fechaRegistro, boolean activo) {
        super(id, nombre, email, password, telefono, oauthProvider, oauthId,
              TipoUsuario.ADMINISTRADOR, fechaRegistro, activo);
    }
}

package com.ecommerce.user.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Vendedor extends User {

    public Vendedor() {
        super();
        setTipo(TipoUsuario.VENDEDOR);
    }

    public Vendedor(UUID id, String nombre, String email, String password, String telefono,
                    String oauthProvider, String oauthId, LocalDateTime fechaRegistro, boolean activo) {
        super(id, nombre, email, password, telefono, oauthProvider, oauthId,
              TipoUsuario.VENDEDOR, fechaRegistro, activo);
    }
}

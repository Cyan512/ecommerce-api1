package com.ecommerce.order.domain.port;

import com.ecommerce.order.domain.model.Cupon;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CuponRepositoryPort {

    Optional<Cupon> findByCodigo(String codigo);

    Cupon save(Cupon cupon);

    List<Cupon> findAll();
}

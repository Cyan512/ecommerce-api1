package com.ecommerce.product.domain.port;

import com.ecommerce.product.domain.model.Producto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoRepositoryPort {

    List<Producto> findAll();

    Optional<Producto> findById(UUID id);

    List<Producto> findByCategoriaId(UUID categoriaId);

    Producto save(Producto producto);

    void deleteById(UUID id);
}

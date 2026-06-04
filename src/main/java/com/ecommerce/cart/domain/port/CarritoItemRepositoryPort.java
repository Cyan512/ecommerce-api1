package com.ecommerce.cart.domain.port;

import com.ecommerce.cart.domain.model.CarritoItem;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarritoItemRepositoryPort {

    List<CarritoItem> findByCarritoId(UUID carritoId);

    Optional<CarritoItem> findByCarritoIdAndProductoId(UUID carritoId, UUID productoId);

    CarritoItem save(CarritoItem item);

    void deleteById(UUID id);

    void deleteByCarritoId(UUID carritoId);
}

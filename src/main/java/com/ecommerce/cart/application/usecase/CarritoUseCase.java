package com.ecommerce.cart.application.usecase;

import com.ecommerce.cart.application.dto.CarritoItemRequest;
import com.ecommerce.cart.application.dto.CarritoResponse;
import com.ecommerce.cart.domain.model.Carrito;
import com.ecommerce.cart.domain.model.CarritoItem;
import com.ecommerce.cart.domain.port.CarritoItemRepositoryPort;
import com.ecommerce.cart.domain.port.CarritoRepositoryPort;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.product.domain.model.Producto;
import com.ecommerce.product.domain.port.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarritoUseCase {

    private final CarritoRepositoryPort carritoRepository;
    private final CarritoItemRepositoryPort carritoItemRepository;
    private final ProductoRepositoryPort productoRepository;

    public CarritoUseCase(CarritoRepositoryPort carritoRepository,
                          CarritoItemRepositoryPort carritoItemRepository,
                          ProductoRepositoryPort productoRepository) {
        this.carritoRepository = carritoRepository;
        this.carritoItemRepository = carritoItemRepository;
        this.productoRepository = productoRepository;
    }

    public CarritoResponse getCarrito(UUID usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuarioId(usuarioId);
                    return carritoRepository.save(nuevo);
                });

        List<CarritoItem> items = carritoItemRepository.findByCarritoId(carrito.getId());
        return buildResponse(carrito, items);
    }

    public CarritoResponse addItem(UUID usuarioId, CarritoItemRequest request) {
        Producto producto = productoRepository.findById(request.getProductoId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto", request.getProductoId()));

        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseGet(() -> {
                    Carrito nuevo = new Carrito();
                    nuevo.setUsuarioId(usuarioId);
                    return carritoRepository.save(nuevo);
                });

        Optional<CarritoItem> existing = carritoItemRepository
                .findByCarritoIdAndProductoId(carrito.getId(), request.getProductoId());

        if (existing.isPresent()) {
            CarritoItem item = existing.get();
            item.setCantidad(item.getCantidad() + request.getCantidad());
            carritoItemRepository.save(item);
        } else {
            CarritoItem item = new CarritoItem();
            item.setCarritoId(carrito.getId());
            item.setProductoId(request.getProductoId());
            item.setCantidad(request.getCantidad());
            carritoItemRepository.save(item);
        }

        List<CarritoItem> items = carritoItemRepository.findByCarritoId(carrito.getId());
        return buildResponse(carrito, items);
    }

    public void removeItem(UUID usuarioId, UUID itemId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito", usuarioId));
        carritoItemRepository.deleteById(itemId);
    }

    public void clearCarrito(UUID usuarioId) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Carrito", usuarioId));
        carritoItemRepository.deleteByCarritoId(carrito.getId());
    }

    private CarritoResponse buildResponse(Carrito carrito, List<CarritoItem> items) {
        List<CarritoResponse.ItemResponse> itemResponses = items.stream().map(item -> {
            Producto producto = productoRepository.findById(item.getProductoId()).orElse(null);
            BigDecimal precio = producto != null ? producto.getPrecio() : BigDecimal.ZERO;
            BigDecimal subtotal = precio.multiply(BigDecimal.valueOf(item.getCantidad()));
            return new CarritoResponse.ItemResponse(
                    item.getId(), item.getProductoId(),
                    producto != null ? producto.getNombre() : "N/A",
                    item.getCantidad(), precio, subtotal);
        }).collect(Collectors.toList());

        BigDecimal total = itemResponses.stream()
                .map(CarritoResponse.ItemResponse::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoResponse(carrito.getId(), itemResponses, total);
    }
}

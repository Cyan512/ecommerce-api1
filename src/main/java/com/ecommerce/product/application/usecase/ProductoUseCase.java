package com.ecommerce.product.application.usecase;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.product.application.dto.ProductoRequest;
import com.ecommerce.product.application.dto.ProductoResponse;
import com.ecommerce.product.domain.model.Producto;
import com.ecommerce.product.domain.port.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductoUseCase {

    private final ProductoRepositoryPort productoRepository;

    public ProductoUseCase(ProductoRepositoryPort productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoResponse> findAll() {
        return productoRepository.findAll().stream()
                .filter(Producto::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponse findById(UUID id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        return toResponse(producto);
    }

    public List<ProductoResponse> findByCategoria(UUID categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId).stream()
                .filter(Producto::isActivo)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponse create(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoriaId(request.getCategoriaId());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setActivo(true);
        return toResponse(productoRepository.save(producto));
    }

    public ProductoResponse update(UUID id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setStock(request.getStock());
        producto.setCategoriaId(request.getCategoriaId());
        producto.setImagenUrl(request.getImagenUrl());
        return toResponse(productoRepository.save(producto));
    }

    public void delete(UUID id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto", id));
        producto.setActivo(false);
        productoRepository.save(producto);
    }

    private ProductoResponse toResponse(Producto p) {
        return new ProductoResponse(
                p.getId(), p.getNombre(), p.getDescripcion(), p.getPrecio(),
                p.getStock(), p.getCategoriaId(), p.getImagenUrl(), p.isActivo()
        );
    }
}

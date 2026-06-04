package com.ecommerce.category.application.usecase;

import com.ecommerce.category.application.dto.CategoriaRequest;
import com.ecommerce.category.application.dto.CategoriaResponse;
import com.ecommerce.category.domain.model.Categoria;
import com.ecommerce.category.domain.port.CategoriaRepositoryPort;
import com.ecommerce.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoriaUseCase {

    private final CategoriaRepositoryPort categoriaRepository;

    public CategoriaUseCase(CategoriaRepositoryPort categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> findAll() {
        return categoriaRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CategoriaResponse findById(UUID id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
        return toResponse(categoria);
    }

    public CategoriaResponse create(CategoriaRequest request) {
        Categoria categoria = new Categoria();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoria.setPadreId(request.getPadreId());
        return toResponse(categoriaRepository.save(categoria));
    }

    public CategoriaResponse update(UUID id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria", id));
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoria.setPadreId(request.getPadreId());
        return toResponse(categoriaRepository.save(categoria));
    }

    public void delete(UUID id) {
        if (!categoriaRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Categoria", id);
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaResponse toResponse(Categoria c) {
        return new CategoriaResponse(c.getId(), c.getNombre(), c.getDescripcion(), c.getPadreId());
    }
}

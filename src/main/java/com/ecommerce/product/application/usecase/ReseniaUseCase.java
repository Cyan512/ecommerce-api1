package com.ecommerce.product.application.usecase;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.product.application.dto.ReseniaRequest;
import com.ecommerce.product.application.dto.ReseniaResponse;
import com.ecommerce.product.domain.model.Resenia;
import com.ecommerce.product.domain.port.ReseniaRepositoryPort;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReseniaUseCase {

    private final ReseniaRepositoryPort reseniaRepository;
    private final UserRepositoryPort userRepository;

    public ReseniaUseCase(ReseniaRepositoryPort reseniaRepository,
                          UserRepositoryPort userRepository) {
        this.reseniaRepository = reseniaRepository;
        this.userRepository = userRepository;
    }

    public List<ReseniaResponse> findByProducto(UUID productoId) {
        return reseniaRepository.findByProductoId(productoId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ReseniaResponse create(UUID usuarioId, ReseniaRequest request) {
        if (reseniaRepository.findByUsuarioIdAndProductoId(usuarioId, request.getProductoId()).isPresent()) {
            throw new BusinessException("Ya has reseñado este producto");
        }

        Resenia resenia = new Resenia();
        resenia.setUsuarioId(usuarioId);
        resenia.setProductoId(request.getProductoId());
        resenia.setCalificacion(request.getCalificacion());
        resenia.setComentario(request.getComentario());
        resenia.setFecha(LocalDateTime.now());
        return toResponse(reseniaRepository.save(resenia));
    }

    public void delete(UUID id, UUID usuarioId) {
        Resenia resenia = reseniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resenia", id));
        if (!resenia.getUsuarioId().equals(usuarioId)) {
            throw new BusinessException("No puedes eliminar una reseña de otro usuario");
        }
        reseniaRepository.deleteById(id);
    }

    private ReseniaResponse toResponse(Resenia r) {
        User user = userRepository.findById(r.getUsuarioId()).orElse(null);
        return new ReseniaResponse(
                r.getId(), r.getUsuarioId(), user != null ? user.getNombre() : null,
                r.getProductoId(), r.getCalificacion(), r.getComentario(), r.getFecha()
        );
    }
}

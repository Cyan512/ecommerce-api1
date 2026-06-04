package com.ecommerce.user.application.usecase;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.user.application.dto.DireccionRequest;
import com.ecommerce.user.application.dto.DireccionResponse;
import com.ecommerce.user.domain.model.Direccion;
import com.ecommerce.user.domain.port.DireccionRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DireccionUseCase {

    private final DireccionRepositoryPort direccionRepository;

    public DireccionUseCase(DireccionRepositoryPort direccionRepository) {
        this.direccionRepository = direccionRepository;
    }

    public List<DireccionResponse> findByUsuario(UUID usuarioId) {
        return direccionRepository.findByUsuarioId(usuarioId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public DireccionResponse create(UUID usuarioId, DireccionRequest request) {
        Direccion direccion = new Direccion();
        direccion.setUsuarioId(usuarioId);
        direccion.setCalle(request.getCalle());
        direccion.setColonia(request.getColonia());
        direccion.setCiudad(request.getCiudad());
        direccion.setEstado(request.getEstado());
        direccion.setCodigoPostal(request.getCodigoPostal());
        direccion.setPais(request.getPais());
        direccion.setPrincipal(request.isPrincipal());
        return toResponse(direccionRepository.save(direccion));
    }

    public void delete(UUID id, UUID usuarioId) {
        Direccion direccion = direccionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Direccion", id));
        if (!direccion.getUsuarioId().equals(usuarioId)) {
            throw new RuntimeException("No puedes eliminar una dirección de otro usuario");
        }
        direccionRepository.deleteById(id);
    }

    private DireccionResponse toResponse(Direccion d) {
        return new DireccionResponse(
                d.getId(), d.getCalle(), d.getColonia(), d.getCiudad(),
                d.getEstado(), d.getCodigoPostal(), d.getPais(), d.isPrincipal()
        );
    }
}

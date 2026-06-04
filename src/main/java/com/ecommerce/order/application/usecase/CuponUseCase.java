package com.ecommerce.order.application.usecase;

import com.ecommerce.order.domain.model.Cupon;
import com.ecommerce.order.domain.port.CuponRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CuponUseCase {

    private final CuponRepositoryPort cuponRepository;

    public CuponUseCase(CuponRepositoryPort cuponRepository) {
        this.cuponRepository = cuponRepository;
    }

    public Cupon findByCodigo(String codigo) {
        return cuponRepository.findByCodigo(codigo)
                .orElse(null);
    }
}

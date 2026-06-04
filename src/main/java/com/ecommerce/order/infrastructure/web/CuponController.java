package com.ecommerce.order.infrastructure.web;

import com.ecommerce.order.domain.model.Cupon;
import com.ecommerce.order.application.usecase.CuponUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/coupons")
public class CuponController {

    private final CuponUseCase cuponUseCase;

    public CuponController(CuponUseCase cuponUseCase) {
        this.cuponUseCase = cuponUseCase;
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable String codigo) {
        Cupon cupon = cuponUseCase.findByCodigo(codigo);
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
                "codigo", cupon.getCodigo(),
                "tipoDescuento", cupon.getTipoDescuento().name(),
                "valorDescuento", cupon.getValorDescuento(),
                "montoMinimo", cupon.getMontoMinimo(),
                "activo", cupon.isActivo()
        ));
    }
}

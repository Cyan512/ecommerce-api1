package com.ecommerce.order.infrastructure.web;

import com.ecommerce.order.application.dto.PedidoResponse;
import com.ecommerce.order.application.usecase.PedidoUseCase;
import com.ecommerce.order.domain.model.EstadoPedido;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminPedidoController {

    private final PedidoUseCase pedidoUseCase;

    public AdminPedidoController(PedidoUseCase pedidoUseCase) {
        this.pedidoUseCase = pedidoUseCase;
    }

    @GetMapping("/orders")
    public ResponseEntity<List<PedidoResponse>> findAll() {
        return ResponseEntity.ok(pedidoUseCase.findByUsuario(null));
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<PedidoResponse> updateStatus(@PathVariable UUID id,
                                                       @RequestBody Map<String, String> body) {
        EstadoPedido estado = EstadoPedido.valueOf(body.get("estado").toUpperCase());
        return ResponseEntity.ok(pedidoUseCase.updateStatus(id, estado));
    }
}

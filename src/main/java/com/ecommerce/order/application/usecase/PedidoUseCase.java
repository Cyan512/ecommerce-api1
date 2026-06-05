package com.ecommerce.order.application.usecase;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.order.application.dto.PedidoRequest;
import com.ecommerce.order.application.dto.PedidoResponse;
import com.ecommerce.order.domain.model.*;
import com.ecommerce.order.domain.port.CuponRepositoryPort;
import com.ecommerce.order.domain.port.PedidoItemRepositoryPort;
import com.ecommerce.order.domain.port.PedidoRepositoryPort;
import com.ecommerce.product.domain.model.Producto;
import com.ecommerce.product.domain.port.ProductoRepositoryPort;
import com.ecommerce.user.domain.model.User;
import com.ecommerce.user.domain.port.UserRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoUseCase {

    private final PedidoRepositoryPort pedidoRepository;
    private final PedidoItemRepositoryPort pedidoItemRepository;
    private final ProductoRepositoryPort productoRepository;
    private final CuponRepositoryPort cuponRepository;
    private final UserRepositoryPort userRepository;

    public PedidoUseCase(PedidoRepositoryPort pedidoRepository,
                         PedidoItemRepositoryPort pedidoItemRepository,
                         ProductoRepositoryPort productoRepository,
                         CuponRepositoryPort cuponRepository,
                         UserRepositoryPort userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoItemRepository = pedidoItemRepository;
        this.productoRepository = productoRepository;
        this.cuponRepository = cuponRepository;
        this.userRepository = userRepository;
    }

    public List<PedidoResponse> findByUsuario(UUID usuarioId) {
        List<Pedido> pedidos = usuarioId != null
                ? pedidoRepository.findByUsuarioId(usuarioId)
                : pedidoRepository.findAll();
        return pedidos.stream()
                .map(this::buildResponse)
                .collect(Collectors.toList());
    }

    public PedidoResponse findById(UUID id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        return buildResponse(pedido);
    }

    public PedidoResponse create(UUID usuarioId, PedidoRequest request) {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (PedidoRequest.ItemRequest itemReq : request.getItems()) {
            Producto producto = productoRepository.findById(itemReq.getProductoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Producto", itemReq.getProductoId()));
            if (producto.getStock() < itemReq.getCantidad()) {
                throw new BusinessException("Stock insuficiente para: " + producto.getNombre());
            }
            subtotal = subtotal.add(producto.getPrecio().multiply(BigDecimal.valueOf(itemReq.getCantidad())));
        }

        BigDecimal descuento = BigDecimal.ZERO;
        if (request.getCuponCodigo() != null) {
            Cupon cupon = cuponRepository.findByCodigo(request.getCuponCodigo())
                    .orElseThrow(() -> new ResourceNotFoundException("Cupon", request.getCuponCodigo()));
            if (!cupon.isActivo() || cupon.getUsosActuales() >= cupon.getUsosMaximos()) {
                throw new BusinessException("Cupón no válido o agotado");
            }
            if (subtotal.compareTo(cupon.getMontoMinimo()) < 0) {
                throw new BusinessException("Monto mínimo no alcanzado para el cupón");
            }
            descuento = cupon.getTipoDescuento() == TipoDescuento.PORCENTAJE
                    ? subtotal.multiply(cupon.getValorDescuento()).divide(BigDecimal.valueOf(100))
                    : cupon.getValorDescuento();
            cupon.setUsosActuales(cupon.getUsosActuales() + 1);
            cuponRepository.save(cupon);
        }

        BigDecimal total = subtotal.subtract(descuento);
        if (total.compareTo(BigDecimal.ZERO) < 0) total = BigDecimal.ZERO;

        Pedido pedido = new Pedido();
        pedido.setUsuarioId(usuarioId);
        pedido.setDireccionId(request.getDireccionId());
        pedido.setSubtotal(subtotal);
        pedido.setDescuento(descuento);
        pedido.setTotal(total);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido = pedidoRepository.save(pedido);

        for (PedidoRequest.ItemRequest itemReq : request.getItems()) {
            Producto producto = productoRepository.findById(itemReq.getProductoId()).get();
            producto.setStock(producto.getStock() - itemReq.getCantidad());
            productoRepository.save(producto);

            PedidoItem item = new PedidoItem();
            item.setPedidoId(pedido.getId());
            item.setProductoId(itemReq.getProductoId());
            item.setCantidad(itemReq.getCantidad());
            item.setPrecioUnitario(producto.getPrecio());
            item.setSubtotal(producto.getPrecio().multiply(BigDecimal.valueOf(itemReq.getCantidad())));
            pedidoItemRepository.save(item);
        }

        return buildResponse(pedido);
    }

    public PedidoResponse updateStatus(UUID id, EstadoPedido nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        pedido.setEstado(nuevoEstado);
        pedido.setFechaActualizacion(LocalDateTime.now());
        return buildResponse(pedidoRepository.save(pedido));
    }

    private PedidoResponse buildResponse(Pedido pedido) {
        List<PedidoItem> items = pedidoItemRepository.findByPedidoId(pedido.getId());
        List<PedidoResponse.ItemResponse> itemResponses = items.stream().map(item -> {
            Producto producto = productoRepository.findById(item.getProductoId()).orElse(null);
            return new PedidoResponse.ItemResponse(
                    item.getProductoId(),
                    producto != null ? producto.getNombre() : "N/A",
                    item.getCantidad(), item.getPrecioUnitario(), item.getSubtotal());
        }).collect(Collectors.toList());

        String email = userRepository.findById(pedido.getUsuarioId())
                .map(User::getEmail).orElse(null);

        return new PedidoResponse(pedido.getId(), email, pedido.getEstado().name(),
                pedido.getTotal(), pedido.getFechaCreacion(), itemResponses);
    }
}

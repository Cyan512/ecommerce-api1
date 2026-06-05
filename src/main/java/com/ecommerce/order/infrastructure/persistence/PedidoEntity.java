package com.ecommerce.order.infrastructure.persistence;

import com.ecommerce.order.domain.model.EstadoPedido;
import com.ecommerce.user.infrastructure.persistence.DireccionEntity;
import com.ecommerce.user.infrastructure.persistence.UserEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private UserEntity usuario;

    @Column(name = "direccion_id", nullable = false)
    private UUID direccionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_id", insertable = false, updatable = false)
    private DireccionEntity direccion;

    @Column(name = "staff_id")
    private UUID staffId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", insertable = false, updatable = false)
    private UserEntity staff;

    @Column(name = "cupon_id")
    private UUID cuponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cupon_id", insertable = false, updatable = false)
    private CuponEntity cupon;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(precision = 10, scale = 2)
    private BigDecimal descuento;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
    public UserEntity getUsuario() { return usuario; }
    public void setUsuario(UserEntity usuario) { this.usuario = usuario; }
    public UUID getDireccionId() { return direccionId; }
    public void setDireccionId(UUID direccionId) { this.direccionId = direccionId; }
    public DireccionEntity getDireccion() { return direccion; }
    public void setDireccion(DireccionEntity direccion) { this.direccion = direccion; }
    public UUID getStaffId() { return staffId; }
    public void setStaffId(UUID staffId) { this.staffId = staffId; }
    public UserEntity getStaff() { return staff; }
    public void setStaff(UserEntity staff) { this.staff = staff; }
    public UUID getCuponId() { return cuponId; }
    public void setCuponId(UUID cuponId) { this.cuponId = cuponId; }
    public CuponEntity getCupon() { return cupon; }
    public void setCupon(CuponEntity cupon) { this.cupon = cupon; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }
}

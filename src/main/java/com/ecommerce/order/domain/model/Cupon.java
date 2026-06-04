package com.ecommerce.order.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Cupon {

    private UUID id;
    private String codigo;
    private TipoDescuento tipoDescuento;
    private BigDecimal valorDescuento;
    private BigDecimal montoMinimo;
    private int usosMaximos;
    private int usosActuales;
    private LocalDateTime fechaExpiracion;
    private boolean activo;

    public Cupon() {}

    public Cupon(UUID id, String codigo, TipoDescuento tipoDescuento, BigDecimal valorDescuento,
                 BigDecimal montoMinimo, int usosMaximos, int usosActuales,
                 LocalDateTime fechaExpiracion, boolean activo) {
        this.id = id;
        this.codigo = codigo;
        this.tipoDescuento = tipoDescuento;
        this.valorDescuento = valorDescuento;
        this.montoMinimo = montoMinimo;
        this.usosMaximos = usosMaximos;
        this.usosActuales = usosActuales;
        this.fechaExpiracion = fechaExpiracion;
        this.activo = activo;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public TipoDescuento getTipoDescuento() { return tipoDescuento; }
    public void setTipoDescuento(TipoDescuento tipoDescuento) { this.tipoDescuento = tipoDescuento; }
    public BigDecimal getValorDescuento() { return valorDescuento; }
    public void setValorDescuento(BigDecimal valorDescuento) { this.valorDescuento = valorDescuento; }
    public BigDecimal getMontoMinimo() { return montoMinimo; }
    public void setMontoMinimo(BigDecimal montoMinimo) { this.montoMinimo = montoMinimo; }
    public int getUsosMaximos() { return usosMaximos; }
    public void setUsosMaximos(int usosMaximos) { this.usosMaximos = usosMaximos; }
    public int getUsosActuales() { return usosActuales; }
    public void setUsosActuales(int usosActuales) { this.usosActuales = usosActuales; }
    public LocalDateTime getFechaExpiracion() { return fechaExpiracion; }
    public void setFechaExpiracion(LocalDateTime fechaExpiracion) { this.fechaExpiracion = fechaExpiracion; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}

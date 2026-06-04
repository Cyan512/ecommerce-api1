package com.ecommerce.order.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Envio {

    private UUID id;
    private UUID pedidoId;
    private String direccion;
    private String transportista;
    private String numeroGuia;
    private EstadoEnvio estado;
    private LocalDateTime fechaEnvio;
    private LocalDateTime fechaEntrega;

    public Envio() {}

    public Envio(UUID id, UUID pedidoId, String direccion, String transportista,
                 String numeroGuia, EstadoEnvio estado, LocalDateTime fechaEnvio,
                 LocalDateTime fechaEntrega) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.direccion = direccion;
        this.transportista = transportista;
        this.numeroGuia = numeroGuia;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getPedidoId() { return pedidoId; }
    public void setPedidoId(UUID pedidoId) { this.pedidoId = pedidoId; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public String getTransportista() { return transportista; }
    public void setTransportista(String transportista) { this.transportista = transportista; }
    public String getNumeroGuia() { return numeroGuia; }
    public void setNumeroGuia(String numeroGuia) { this.numeroGuia = numeroGuia; }
    public EstadoEnvio getEstado() { return estado; }
    public void setEstado(EstadoEnvio estado) { this.estado = estado; }
    public LocalDateTime getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDateTime fechaEnvio) { this.fechaEnvio = fechaEnvio; }
    public LocalDateTime getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(LocalDateTime fechaEntrega) { this.fechaEntrega = fechaEntrega; }
}

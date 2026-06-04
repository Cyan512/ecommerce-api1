package com.ecommerce.user.application.dto;

import jakarta.validation.constraints.NotBlank;

public class DireccionRequest {

    @NotBlank
    private String calle;

    @NotBlank
    private String colonia;

    @NotBlank
    private String ciudad;

    @NotBlank
    private String estado;

    @NotBlank
    private String codigoPostal;

    @NotBlank
    private String pais;

    private boolean principal;

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }
    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) { this.codigoPostal = codigoPostal; }
    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }
    public boolean isPrincipal() { return principal; }
    public void setPrincipal(boolean principal) { this.principal = principal; }
}

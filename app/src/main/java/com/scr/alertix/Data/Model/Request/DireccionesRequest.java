package com.scr.alertix.Data.Model.Request;

public class DireccionesRequest {
    private String barrio;
    private String direccion;
    private String pais;
    private String ciudad;
    private String departamento;
    private String municipio;

    private String codigoPostal;
    private double latitud;
    private double longitud;

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}

package com.scr.Alertix_DB.Model;

public class LoginRequest {
    private String usuario;
    private String contrasenia;

    // Getters y Setters (¡Súper importantes para que funcione!)
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContrasenia() { return contrasenia; }
    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
}
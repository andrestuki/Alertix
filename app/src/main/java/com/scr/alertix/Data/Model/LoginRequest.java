package com.scr.alertix.Data.Model;

public class LoginRequest {
    private String usuario;
    private String contrasenia;

    public LoginRequest(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }
}

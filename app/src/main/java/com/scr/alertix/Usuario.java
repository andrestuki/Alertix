package com.scr.alertix;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private String correo;
    private String contraseña;
    private String tipo;

    public Usuario(String nombre, String correo, String contraseña, String tipo) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getTipo() {
        return tipo;
    }
}

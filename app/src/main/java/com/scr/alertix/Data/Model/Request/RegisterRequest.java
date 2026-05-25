package com.scr.alertix.Data.Model.Request;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String genero;
    private String email;
    private String password;
    private int idProfile;

    public RegisterRequest() {
    }

    public RegisterRequest(String nombre, String apellido, String genero, String email, String password, int idProfile) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.genero = genero;
        this.email = email;
        this.password = password;
        this.idProfile = idProfile;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}

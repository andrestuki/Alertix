package com.scr.alertix.Data.Model.Request;

import java.time.LocalDateTime;

public class RegisterRequest {
    private String usuarioNombre;     // Coincide con Backend
    private String nombre;
    private String apellido;
    private String generoUsuario;     // Coincide con Backend
    private String correoUsuario;     // Coincide con Backend
    private String contraseniaUsuario; // Coincide con Backend
    private String fechaNacimiento;    // Usar String (yyyy-MM-dd) para facilitar envío
    private DireccionId idDireccion;
    private String imgPerfil;
    private int idProfile;

    public RegisterRequest() {
    }

    public static class DireccionId {
        private Long idDireccion;

        public DireccionId(Long idDireccion) {
            this.idDireccion = idDireccion;
        }
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setGeneroUsuario(String generoUsuario) {
        this.generoUsuario = generoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public void setContraseniaUsuario(String contraseniaUsuario) {
        this.contraseniaUsuario = contraseniaUsuario;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setIdDireccion(DireccionId idDireccion) {
        this.idDireccion = idDireccion;
    }

    public void setImgPerfil(String imgPerfil) {
        this.imgPerfil = imgPerfil;
    }

    public void setIdProfile(int idProfile) {
        this.idProfile = idProfile;
    }
}

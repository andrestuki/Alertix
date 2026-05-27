package com.scr.Alertix_DB.Model;

public class LoginResponse {
    private Long idUsuario;
    private Integer idProfile;
    private Long idDireccion;
    private String estado;
    private String mensaje;

    // Constructor para login exitoso
    public LoginResponse(Long idUsuario,  Integer idProfile,Long idDireccion) {
        this.idUsuario = idUsuario;
        this.idProfile = idProfile;
        this.idDireccion = idDireccion;
        this.mensaje = "Login exitoso";
    }

    // Constructor para error
    public LoginResponse(String mensaje) {
        this.estado = "error";
        this.mensaje = mensaje;
    }

    // Getters y Setters (Necesarios para que Spring cree el JSON)
    public Long getIdUsuario() { return idUsuario; }
    public Integer getIdProfile() { return idProfile; }
    public String getEstado() { return estado; }
    public String getMensaje() { return mensaje; }
    public Long getIdDireccion() {
        return idDireccion;
    }
}

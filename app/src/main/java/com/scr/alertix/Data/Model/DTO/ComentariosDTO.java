package com.scr.alertix.Data.Model.DTO;

import com.google.gson.annotations.SerializedName;

public class ComentariosDTO {

    @SerializedName("idComentarios")
    private int idComentarios;

    @SerializedName("idComentarioHijo")
    private Integer idComentarioHijo; // Usamos Integer por si viene NULL de la BD

    @SerializedName("idUsuario")
    private int idUsuario;

    @SerializedName("usuarioNombre")
    private String nombreUsuario;

    @SerializedName("imgPerfil")
    private String fotoPerfil;

    @SerializedName("comentario")
    private String comentario;

    @SerializedName("fechaComentario")
    private String fechaComentario;


    private boolean isLikedLocal = false;
    private int contadorLikesLocal = 0;

    public ComentariosDTO() {
    }


    public int getIdComentarios() {
        return idComentarios;
    }

    public void setIdComentarios(int idComentarios) {
        this.idComentarios = idComentarios;
    }

    public Integer getIdComentarioHijo() {
        return idComentarioHijo;
    }

    public void setIdComentarioHijo(Integer idComentarioHijo) {
        this.idComentarioHijo = idComentarioHijo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(String fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    // --- GETTERS Y SETTERS DE LA LÓGICA LOCAL ---
    public boolean isLikedLocal() {
        return isLikedLocal;
    }

    public void setLikedLocal(boolean likedLocal) {
        this.isLikedLocal = likedLocal;
    }

    public int getContadorLikesLocal() {
        return contadorLikesLocal;
    }

    public void setContadorLikesLocal(int contadorLikesLocal) {
        this.contadorLikesLocal = contadorLikesLocal;
    }
}

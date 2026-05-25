package com.scr.alertix.Data.Model.DTO;

import com.google.gson.annotations.SerializedName;

public class PublicacionDTO {

    @SerializedName("idUsuario")
    private int idUsuario;

    @SerializedName("usuarioNombre")
    private String nombreUsuario;

    @SerializedName("imgPerfil")
    private String fotoPerfil;

    @SerializedName("idPublicacion")
    private int id;

    @SerializedName("descripcion")
    private String descripcion;

    @SerializedName("barrio")
    private String barrio;

    @SerializedName("imgPublicacion")
    private String imagenPublicacion;

    @SerializedName("fecha")
    private String fecha;

    @SerializedName("nombreCategoria")
    private String categoria;

    @SerializedName("cantidadLikes")
    private int likes;

    @SerializedName("cantidadComentarios")
    private int comentarios;

    @SerializedName("cantidadCompartidos")
    private int compartidos;

    @SerializedName("isLiked")
    private int isLiked;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isLiked() {
        return isLiked == 1; // Convertir 1 a true, 0 a false
    }

    public void setLiked(boolean liked) {
        this.isLiked = liked ? 1 : 0; // Convertir true a 1, false a 0
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBarrio() {
        return barrio;
    }

    public String getImagenPublicacion() {
        return imagenPublicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getLikes() {
        return likes;
    }

    public int getComentarios() {
        return comentarios;
    }

    public int getCompartidos() {
        return compartidos;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setImagenPublicacion(String imagenPublicacion) {
        this.imagenPublicacion = imagenPublicacion;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setComentarios(int comentarios) {
        this.comentarios = comentarios;
    }

    public void setCompartidos(int compartidos) {
        this.compartidos = compartidos;
    }
}

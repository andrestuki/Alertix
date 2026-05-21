package com.scr.alertix.Data.Model;

import com.google.gson.annotations.SerializedName;

public class PublicacionDTO {

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

    // Estado local para el Like
    private boolean isLiked = false;

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
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

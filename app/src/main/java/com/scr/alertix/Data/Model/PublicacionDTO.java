package com.scr.alertix.Data.Model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;


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

        @SerializedName("imgPublicacion") // Coincide con el ALIAS de tu SQL
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
}

package com.scr.alertix.Data.Model.Request;

import com.google.gson.annotations.SerializedName;

public class ComentariosRequest {
    private String comentario;

    @SerializedName("idComentarioHijo")
    private Integer idComentarioHijo;

    private UsuarioId idUsuario;
    private PublicacionId idPublicacion;

    public static class UsuarioId {
        private Long idUsuario;
        public UsuarioId(Long id) { this.idUsuario = id; }
    }

    public static class PublicacionId {
        private Long idPublicacion;
        public PublicacionId(Long id) { this.idPublicacion = id; }
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setIdComentarioHijo(Integer idComentarioHijo) {
        this.idComentarioHijo = idComentarioHijo; // Corregido: Asignación correcta
    }

    public void setIdUsuario(UsuarioId idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setIdPublicacion(PublicacionId idPublicacion) {
        this.idPublicacion = idPublicacion;
    }
}

package com.scr.alertix.Data.Model.Request;

public class LikeRequest {
    private PublicacionId idPublicacion;
    private UsuarioId idUsuario;

    public static class UsuarioId {
        private Long idUsuario;
        public UsuarioId(Long id) { this.idUsuario = id; }
    }

    public static class PublicacionId {
        private Integer idPublicacion;
        public PublicacionId(Integer id) { this.idPublicacion = id; }
    }

    public PublicacionId getIdPublicacion() {
        return idPublicacion;
    }

    public UsuarioId getIdUsuario() {
        return idUsuario;
    }

    public void setIdPublicacion(PublicacionId idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setIdUsuario(UsuarioId idUsuario) {
        this.idUsuario = idUsuario;
    }
}

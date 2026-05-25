package com.scr.alertix.Pojo;

public class Likes {
    private Long idLikes;
    private Long idPublicacion;
    private Long idUsuario;

    public Likes(Long idLikes, Long idPublicacion, Long idUsuario) {
        this.idLikes = idLikes;
        this.idPublicacion = idPublicacion;
        this.idUsuario = idUsuario;
    }

    public void setIdLikes(Long idLikes) {
        this.idLikes = idLikes;
    }

    public void setIdPublicacion(Long idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdLikes() {
        return idLikes;
    }

    public Long getIdPublicacion() {
        return idPublicacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
}

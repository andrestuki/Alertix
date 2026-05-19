package com.scr.Alertix_DB.Interface;

import java.time.LocalDateTime;

public interface ComentariosDetalleDTO {
    Integer getIdComentarios();


    Integer getIdComentarioHijo();


    Integer getIdUsuario();
    String getUsuarioNombre();
    String getImgPerfil();

    String getComentario();
    LocalDateTime getFechaComentario();
}

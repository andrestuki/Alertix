package com.scr.Alertix_DB.Interface;

import java.time.LocalDateTime;

public interface PublicacionDetalleDTO {
    String getUsuarioNombre();
    String getImgPerfil();
    Long getIdPublicacion();
    String getDescripcion();
    String getBarrio();
    String getImgPublicacion();
    LocalDateTime getFecha();
    String getNombreCategoria();
    Integer getCantidadLikes();
    Integer getCantidadComentarios();
    Integer getCantidadCompartidos();
}

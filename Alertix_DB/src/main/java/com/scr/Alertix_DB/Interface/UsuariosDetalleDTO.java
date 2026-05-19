package com.scr.Alertix_DB.Interface;


import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;

public interface UsuariosDetalleDTO {

    Integer getIdUsuario();
    String getUsuarioNombre();
    String getImgPerfil();


    Integer getIdPublicacion();
    String getDescripcion();
    String getImgPublicacion();
    LocalDateTime getFecha();
    String getNombreCategoria();


    String getPais();
    String getCiudad();


    Integer getTotalLikesRecibidos();
    Integer getCantidadSeguidores();
    Integer getCantidadSeguidos();
}
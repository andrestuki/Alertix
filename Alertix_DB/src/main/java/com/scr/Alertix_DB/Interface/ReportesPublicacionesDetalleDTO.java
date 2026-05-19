package com.scr.Alertix_DB.Interface;

import java.time.LocalDateTime;

public interface ReportesPublicacionesDetalleDTO {
    Integer getIdPublicacion();

    String getDescripcion();

    String getDireccion();

    String getImgPublicacion();

    LocalDateTime getFecha();

    String getNombreCategoria();

    // El dueño de la publicación
    String getAutorPublicacion();

    // Foto de quien la subió
    String getFotoAutor();

    // Total de quejas (COUNT)
    Integer getCantidadReportes();

    // Lista de nombres (GROUP_CONCAT)
    String getNombresDenunciantes();

}

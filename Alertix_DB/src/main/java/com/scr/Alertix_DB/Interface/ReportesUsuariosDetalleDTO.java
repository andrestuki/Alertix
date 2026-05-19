package com.scr.Alertix_DB.Interface;

import java.time.LocalDateTime;

public interface ReportesUsuariosDetalleDTO {
    Integer getIdReportado();
    String getNombreReportado();
    Integer getIdDenunciante();
    String getNombreDenunciante();
    LocalDateTime getFechaReporte();

}

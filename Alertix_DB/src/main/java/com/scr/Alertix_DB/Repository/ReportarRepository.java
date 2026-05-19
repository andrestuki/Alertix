package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.ReportesPublicacionesDetalleDTO;
import com.scr.Alertix_DB.Interface.ReportesUsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.Reportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportarRepository extends JpaRepository<Reportes, Long> {
    @Procedure(procedureName = "reportarUsuario")
    void reportarUsuario(Long p_idUsuario,Long p_idReportado,Long p_idTipoReportes);

    @Procedure(procedureName = "reportarPublicacion")
    void reportarPublicacion(Long p_idUsuario,Long p_idPublicacion,Long p_idTipoReportes);

    @Procedure(procedureName = "mostrarPublicacionesReportadas")
    List<ReportesPublicacionesDetalleDTO> mostrarPublicacionesReportadas();

    @Procedure(procedureName = "mostrarUsuariosReportados")
    List<ReportesUsuariosDetalleDTO> mostrarUsuariosReportados();


}

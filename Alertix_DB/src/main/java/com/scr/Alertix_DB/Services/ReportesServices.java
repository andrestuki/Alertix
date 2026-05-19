package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.ReportesPublicacionesDetalleDTO;
import com.scr.Alertix_DB.Interface.ReportesUsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.Reportes;
import com.scr.Alertix_DB.Repository.ReportarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportesServices {
    @Autowired
    private ReportarRepository reportarRepository;

    @Transactional
    public void reportarUsuario(Reportes report){
        reportarRepository.reportarUsuario(report.getIdUsuario().getIdUsuario(),report.getIdReportado().getIdUsuario(),report.getIdTipoReportes().getIdTipoReportes());
    }
    @Transactional
    public void reportarPublicacion(Reportes report){
        reportarRepository.reportarPublicacion(report.getIdUsuario().getIdUsuario(),report.getIdPublicacion().getIdPublicacion(),report.getIdTipoReportes().getIdTipoReportes());
    }
    @Transactional
    public List<ReportesUsuariosDetalleDTO> mostrarUsuariosReportados(){

        return reportarRepository.mostrarUsuariosReportados();
    }
    @Transactional
    public List<ReportesPublicacionesDetalleDTO> mostrarPublicacionesReportadas(){
        return reportarRepository.mostrarPublicacionesReportadas();
    }
}

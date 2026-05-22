package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.TipoReportesDTO;
import com.scr.Alertix_DB.Model.TipoBaneos;
import com.scr.Alertix_DB.Model.TipoReportes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoReportesRepository extends JpaRepository<TipoReportes,Long> {
    @Procedure(procedureName = "llenarTipoReportes")
    List<TipoReportesDTO> llenarTipoReportes();

}

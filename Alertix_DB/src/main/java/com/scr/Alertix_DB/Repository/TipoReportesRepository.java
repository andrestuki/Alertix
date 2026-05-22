package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.TipoReportesDTO;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoReportesRepository {
    @Procedure(procedureName = "llenarTipoReportes")
    List<TipoReportesDTO> llenarTipoReportes();

}

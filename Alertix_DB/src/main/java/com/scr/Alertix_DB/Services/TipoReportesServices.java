package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.TipoReportesDTO;
import com.scr.Alertix_DB.Repository.TipoReportesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoReportesServices {
    @Autowired
    private TipoReportesRepository tipoReportesRepository;

    public List<TipoReportesDTO> llenarTipoReportes() {
        return tipoReportesRepository.llenarTipoReportes();
    }
}

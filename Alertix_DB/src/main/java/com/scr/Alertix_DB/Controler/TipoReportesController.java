package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.TipoReportesDTO;
import com.scr.Alertix_DB.Repository.TipoReportesRepository;
import com.scr.Alertix_DB.Services.TipoReportesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TipoReportesController {
    @Autowired
    private TipoReportesServices tipoReportesServices;

    @GetMapping("/llenar-tipo-reportes")
    public ResponseEntity<?> llenarTipoReportes(){
        try{
            List<TipoReportesDTO> tipoReportes=tipoReportesServices.llenarTipoReportes();
            if(tipoReportes.isEmpty()){
                return new ResponseEntity<>("Lista vacia", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tipoReportes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("error: "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

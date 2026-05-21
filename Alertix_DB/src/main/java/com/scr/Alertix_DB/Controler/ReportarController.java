package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.ReportesPublicacionesDetalleDTO;
import com.scr.Alertix_DB.Interface.ReportesUsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.Reportes;
import com.scr.Alertix_DB.Services.ReportesServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ReportarController {
    @Autowired
    private ReportesServices reportesServices;

    @PostMapping("/reportarUsuario")
    public ResponseEntity<?> reportarUsuario(@Valid @RequestBody Reportes repor){
        try{
            reportesServices.reportarUsuario(repor);
            return new ResponseEntity<>("reporte exitoso", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/reportarPublicacion")
    public ResponseEntity<?> reportarPublicacion(@Valid @RequestBody Reportes report){
        try{
            reportesServices.reportarPublicacion(report);
            return new ResponseEntity<>("exito en reportar publicacion",HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error" + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/MostrarUsuariosReportados")
    public ResponseEntity<?>mostrarUsuariosReportados(){
        try{
            List<ReportesUsuariosDetalleDTO> reportes=reportesServices.mostrarUsuariosReportados();
            if(reportes.isEmpty()){
                return new ResponseEntity<>("no hay reportes",HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reportes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/MostrarPublicacionesReportadas")
    public ResponseEntity<?> mostrarPublicacionesReportadas(){
        try{
            List<ReportesPublicacionesDetalleDTO> reportes=reportesServices.mostrarPublicacionesReportadas();
            if(reportes.isEmpty()){
                return new ResponseEntity<>("no hay contenido en reportes",HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reportes,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}

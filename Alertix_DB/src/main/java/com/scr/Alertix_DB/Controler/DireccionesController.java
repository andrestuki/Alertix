package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Model.Direcciones;
import com.scr.Alertix_DB.Services.DireccionesServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DireccionesController {

    @Autowired
    private DireccionesServices direccionesServices;

    @PostMapping("/registrar-direcciones")
    public ResponseEntity<?> registrarDirecciones(@RequestBody Direcciones direcciones){
        try {
            Integer idDireccion = direccionesServices.registrarDireccion(direcciones);
            return new ResponseEntity<>(idDireccion, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

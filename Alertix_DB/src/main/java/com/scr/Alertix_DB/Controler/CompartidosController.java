package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Model.Compartidos;
import com.scr.Alertix_DB.Services.ComentariosServices;
import com.scr.Alertix_DB.Services.CompartidosServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CompartidosController {
    @Autowired
    private CompartidosServices compartidosServices;

    @PostMapping("/compartir")
    public ResponseEntity<?> compartir(@Valid @RequestBody Compartidos compa){
        try{
            Integer compartidos= compartidosServices.compartir(compa);
            return new  ResponseEntity<>(compartidos,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }
}

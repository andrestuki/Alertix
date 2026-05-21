package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Model.Seguidores;
import com.scr.Alertix_DB.Services.SeguidoresServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SeguidoresController {
    @Autowired
    private SeguidoresServices seguidoresServices;

    @PostMapping("/seguir")
    public ResponseEntity<?>seguir(@Valid @RequestBody Seguidores seg){
        try{
            Integer seguidores=seguidoresServices.seguir(seg);
            return new ResponseEntity<>(seguidores, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error: " + e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}

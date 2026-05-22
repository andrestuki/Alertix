package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.TipoBaneoDTO;
import com.scr.Alertix_DB.Services.TipoBaneosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TipoBaneosController {

    @Autowired
    private TipoBaneosServices tipoBaneosServices;

    @GetMapping("/llenar-tipo-baneos")
    public ResponseEntity<?> llenarTipoBaneos(){
        try{
            List<TipoBaneoDTO> tipoBaneos=tipoBaneosServices.llenarTipoBaneos();

            if(tipoBaneos.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tipoBaneos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error: "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.TipoCategoriasDTO;
import com.scr.Alertix_DB.Repository.CategoriasRepository;
import com.scr.Alertix_DB.Services.TipoCategoriasServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TipoCategoriasController {
    @Autowired
    private TipoCategoriasServices tipoCategoriasServices;

    @GetMapping("/llenar-tipo-categorias")
    public ResponseEntity<?> llenarTipoCategorias() {
        try{
            List<TipoCategoriasDTO> categorias = tipoCategoriasServices.llenarTipoCategorias();

            if(categorias.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categorias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error: "+ e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

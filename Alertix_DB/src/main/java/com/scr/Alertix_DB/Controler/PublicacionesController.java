package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.PublicacionDetalleDTO;
import com.scr.Alertix_DB.Model.Publicaciones;
import com.scr.Alertix_DB.Model.Usuarios;
import com.scr.Alertix_DB.Services.PublicacionesServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PublicacionesController {
    @Autowired
    private PublicacionesServices publicacionesServices;

    @PostMapping("/AgregarPublicacion")
    public ResponseEntity<?> agregarPublicacion(@Valid @RequestBody Publicaciones publi) {
        try {
            Integer idReciente= publicacionesServices.agragarPublicaciones(publi);
            return new ResponseEntity<>(idReciente, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al registrar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrarPublicaciones")
    public ResponseEntity<?> mostrarPublicaciones(@RequestParam Long idUsuario) {
        try {
            List<PublicacionDetalleDTO> lista = publicacionesServices.mostrarPublicacion(idUsuario);

            if (lista.isEmpty()) {
                return new ResponseEntity<>("No hay publicaciones para mostrar", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lista, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el feed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/filtrar-publicaciones")
    public ResponseEntity<?> filtrarPublicaciones(@RequestParam(required = false) Integer idCategoria, @RequestParam(required = false) Integer idDireccion) {
        try {
            List<PublicacionDetalleDTO> lista = publicacionesServices.filtrarPublicacion(idCategoria, idDireccion);
            if (lista.isEmpty()) {
                return new ResponseEntity<>("No hay publicaciones para mostrar", HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error al obtener el feed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("Eliminar-Publicaciones")
    public ResponseEntity<?> eliminarPublicacion(@Valid @RequestBody Publicaciones publi) {
        try{
            publicacionesServices.eliminarPublicacion(publi);
            return new ResponseEntity<>("Alerta eliminada", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

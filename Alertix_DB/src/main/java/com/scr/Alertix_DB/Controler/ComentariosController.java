package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.ComentariosDetalleDTO;
import com.scr.Alertix_DB.Model.Comentarios;
import com.scr.Alertix_DB.Services.ComentariosServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class ComentariosController {
    @Autowired
    private ComentariosServices comentariosServices;

    @PostMapping("/comentar")
    public ResponseEntity<?> comentar(@Valid @RequestBody Comentarios com){
        try{
           Integer comentario = comentariosServices.comentar(com);
            return new ResponseEntity<>(comentario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al comentar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/Responder")
    public ResponseEntity<?> responderComentario(@Valid @RequestBody Comentarios com){
        try {
            // AUDITORÍA DE ENTRADA: Verificamos qué llega realmente al servidor
            System.out.println("--- LOG DE AUDITORÍA (SERVIDOR) ---");
            System.out.println("ID Padre Recibido: " + com.getIdComentarioHijo());
            System.out.println("Texto: " + com.getComentario());

            Integer total = comentariosServices.guardarRespuesta(com);
            return new ResponseEntity<>(total, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error en respuesta: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/mostrar-comentario")
    public ResponseEntity<?> mostrarComentarios(@RequestParam Long idPublicacion){
        try {
            List<ComentariosDetalleDTO> comentarios = comentariosServices.mostrarComentarios(idPublicacion);
            if (comentarios.isEmpty()) {
                return new ResponseEntity<>("Comentarios no encontrados", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(comentarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al mostrar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/eliminar-comentario")
    public ResponseEntity<?> eliminarComentario(@RequestParam Long idComentario, @RequestParam Long idUsuario){
        try {
           Integer total = comentariosServices.eliminarComentario(idComentario, idUsuario);
            return new ResponseEntity<>(total, HttpStatus.CREATED);
        } catch (Exception e) {
           return new ResponseEntity<>("Error al eliminar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

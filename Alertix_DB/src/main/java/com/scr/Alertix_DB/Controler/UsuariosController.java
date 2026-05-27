package com.scr.Alertix_DB.Controler;

import com.scr.Alertix_DB.Interface.UsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.LoginRequest;
import com.scr.Alertix_DB.Model.LoginResponse;
import com.scr.Alertix_DB.Model.Usuarios;
import com.scr.Alertix_DB.Services.UsuariosServices;
import jakarta.validation.Valid;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UsuariosController {
    @Autowired
    private UsuariosServices usuariosService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@Valid @RequestBody Usuarios usuario) {
        try {
            Integer idUsuario =   usuariosService.registrarNuevoUsuario(usuario);

            return new ResponseEntity<>(idUsuario, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error en registro: " + e.getMessage());
            return new ResponseEntity<>("Error al registrar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login(@Valid @RequestBody LoginRequest request) {
        try {
            // 1. Guardamos la respuesta del servicio
            LoginResponse respuesta = usuariosService.validarLogin(request.getUsuario(), request.getContrasenia());

            // 2. Verificamos si el ID es nulo o 0 (Usuario no encontrado/Contraseña mal)
            if (respuesta.getIdUsuario() == null || respuesta.getIdUsuario() == 0 ) {
                return new ResponseEntity<>(respuesta.getMensaje(), HttpStatus.UNAUTHORIZED);
            }

            // 3. Si todo está bien, devolvemos el objeto con los datos
            return new ResponseEntity<>(respuesta, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Error en el servidor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mostrar-usuario")
    public ResponseEntity<?> mostrarUsuario(@RequestParam Long idUsuario) {
        try{
            List<UsuariosDetalleDTO> usuarios=usuariosService.mostrarUsuarios(idUsuario);
            if(usuarios.isEmpty()){
                return new ResponseEntity<>("No se encontro el usuario", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}


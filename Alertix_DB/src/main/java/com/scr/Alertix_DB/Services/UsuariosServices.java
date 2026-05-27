package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.UsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.LoginResponse;
import com.scr.Alertix_DB.Model.Usuarios;
import com.scr.Alertix_DB.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.expression.common.ExpressionUtils.toInt;

@Service
public class UsuariosServices {
    @Autowired
    private UsuarioRepository usuariosRepository;

    public Integer registrarNuevoUsuario(Usuarios user) {
        return usuariosRepository.registrarUsuario(
            user.getUsuarioNombre(),
            user.getNombre(),
            user.getApellido(),
            user.getGeneroUsuario(),
            user.getFechaNacimiento().toString(),
            user.getIdDireccion().getIdDireccion(),
            user.getContraseniaUsuario(),
            user.getCorreoUsuario(),
            user.getImgPerfil()
        );
    }
    public LoginResponse validarLogin(String user, String pass) {
        List<Object[]> resultado = usuariosRepository.validarLogin(user, pass);

        if (!resultado.isEmpty()) {
            Object[] datos = resultado.get(0);

            Long idEncontrado = ((Number) datos[0]).longValue();
            Integer perfilEncontrado = ((Number) datos[1]).intValue();
            Long idDireccion = ((Number) datos[2]).longValue();

            if (idEncontrado == 0) {
                return new LoginResponse("Usuario o contraseña incorrectos");
            }

            return new LoginResponse(idEncontrado, perfilEncontrado, idDireccion);

        } else {
            return new LoginResponse("Usuario no encontrado");
        }
    }

    @Transactional
    public List<UsuariosDetalleDTO> mostrarUsuarios(Long idUsuario) {
        return usuariosRepository.mostrarUsuario(idUsuario);
    }


}

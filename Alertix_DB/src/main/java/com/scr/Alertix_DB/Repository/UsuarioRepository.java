package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.UsuariosDetalleDTO;
import com.scr.Alertix_DB.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {
    @Query(value = "CALL registrarUsuario(:nombreUsuario, :nombre, :apellido, :genero, :fechaNac, :idDireccion, :contra, :correo, :img)", nativeQuery = true)
    Integer registrarUsuario(@Param("nombreUsuario") String nombreUsuario,
                             @Param("nombre") String nombre,
                             @Param("apellido") String apellido,
                             @Param("genero") String genero,
                             @Param("fechaNac") String fechaNac,
                             @Param("idDireccion") Long idDireccion,
                             @Param("contra") String contra,
                             @Param("correo") String correo,
                             @Param("img") String img);

    @Query(value = "CALL validarLogin(:p_usuarioNombre, :p_contrasenia)", nativeQuery = true)
    List<Object[]> validarLogin(@Param("p_usuarioNombre") String usuario, @Param("p_contrasenia") String pass);

    @Query(value = "CALL mostrarUsuario(:p_idUsuario) ",nativeQuery=true)
    List<UsuariosDetalleDTO> mostrarUsuario(@Param("p_idUsuario") Long idUsuario);
}


package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.PublicacionDetalleDTO;
import com.scr.Alertix_DB.Model.Categoria;
import com.scr.Alertix_DB.Model.Publicaciones;
import com.scr.Alertix_DB.Model.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Text;

import java.util.List;

@Repository
public interface PublicacionesRepository extends JpaRepository<Publicaciones, Long> {

    @Transactional
    @Query(value = "CALL agregarPublicaciones(:idUsuario, :idCategoria, :descripcion, :idDireccion, :img)", nativeQuery = true)
    Integer agregarPublicaciones(
            @Param("idUsuario") Long idUsuario,
            @Param("idCategoria") Long idCategoria,
            @Param("descripcion") String descripcion,
            @Param("idDireccion") Long idDireccion,
            @Param("img") String img
    );
    @Query(value = "CALL mostrarPublicacion(:idUsuario)", nativeQuery = true)
    List<PublicacionDetalleDTO> mostrarPublicacion(@Param("idUsuario") Long idUsuario);

    @Procedure(procedureName = "filtrarPublicaciones")
    List<PublicacionDetalleDTO> filtrarPublicaciones(Integer idCategoria, Integer direccion);

    @Transactional
    @Query(value = "CALL eliminarPublicacion(:idPublicacion, :idUsuario)", nativeQuery = true)
    void eliminarPublicacion(@Param("idPublicacion") Long idPublicacion, @Param("idUsuario") Long idUsuario);


}

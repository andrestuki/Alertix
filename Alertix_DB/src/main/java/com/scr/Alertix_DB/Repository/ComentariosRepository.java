package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.ComentariosDetalleDTO;
import com.scr.Alertix_DB.Model.Comentarios;
import com.scr.Alertix_DB.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComentariosRepository extends JpaRepository<Comentarios, Long> {

    @Transactional
    @Query(value = "CALL comentar(:idUsuario, :idPublicacion, :comentario)", nativeQuery = true)
    Integer comentar(@Param("idUsuario") Long idUsuario,
                     @Param("idPublicacion") Long idPublicacion,
                     @Param("comentario") String comentario);

    @Transactional
    @Query(value = "CALL responderComentario(:idUsuario, :idPublicacion, :idPadre, :comentario)", nativeQuery = true)
    Integer responderComentario(@Param("idUsuario") Long idUsuario,
                                @Param("idPublicacion") Long idPublicacion,
                                @Param("idPadre") Long idPadre,
                                @Param("comentario") String comentario);

    @Procedure(procedureName = "mostrarComentarios")
    List<ComentariosDetalleDTO> mostrarComentarios(Long idPublicacion);

    @Transactional
    @Query(value = "CALL eliminarComentario(:idComentario, :idUsuario)", nativeQuery = true)
    Integer eliminarComentario(@Param("idComentario") Long idComentario,
                               @Param("idUsuario") Long idUsuario);
}





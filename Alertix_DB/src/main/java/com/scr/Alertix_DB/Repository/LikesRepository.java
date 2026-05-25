package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.LikesDetalleDTO;
import com.scr.Alertix_DB.Model.Likes;
import com.scr.Alertix_DB.Model.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Transactional
    @Query(value = "CALL darLike(:idUsuario, :idPublicacion)", nativeQuery = true)
    List<LikesDetalleDTO> darLike(@Param("idUsuario") Long idUsuario, @Param("idPublicacion") Long idPublicacion);


}

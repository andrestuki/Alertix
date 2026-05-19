package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Model.Seguidores;
import com.scr.Alertix_DB.Model.Usuarios;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeguidoresRepository extends JpaRepository<Seguidores, Long> {

    @Transactional
    @Query(value = "CALL seguirUsuario(:idUsuario, :idSeguidor)", nativeQuery = true)
    Integer seguirUsuario(@Param("idUsuario") Long idUsuario, @Param("idSeguidor") Long idSeguidor);

    @Procedure(procedureName = "dejarSeguir")
    Integer dejarSeguir(Long idUsuario,Long idSeguidor);
}

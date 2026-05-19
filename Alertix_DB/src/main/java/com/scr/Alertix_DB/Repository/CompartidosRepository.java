package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Model.Compartidos;
import com.scr.Alertix_DB.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface CompartidosRepository extends JpaRepository<Compartidos, Long> {

    @Procedure(procedureName = "compartir")
    Integer compartir(Long idUsuario,Long idPublicacion);

}

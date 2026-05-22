package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.TipoBaneoDTO;
import com.scr.Alertix_DB.Model.Categoria;
import com.scr.Alertix_DB.Model.TipoBaneos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoBaneosRepository extends JpaRepository<TipoBaneos,Long> {
    @Procedure(procedureName = "llenarTipoBaneo")
    List<TipoBaneoDTO> llenarTipoBaneos();
}

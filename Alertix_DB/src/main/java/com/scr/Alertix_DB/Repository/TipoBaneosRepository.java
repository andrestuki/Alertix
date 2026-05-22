package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.TipoBaneoDTO;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoBaneosRepository {
    @Procedure(procedureName = "llenarTipoBaneo")
    List<TipoBaneoDTO> llenarTipoBaneos();
}

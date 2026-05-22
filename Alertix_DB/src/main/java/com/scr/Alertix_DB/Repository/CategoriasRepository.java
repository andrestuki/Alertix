package com.scr.Alertix_DB.Repository;

import com.scr.Alertix_DB.Interface.TipoCategoriasDTO;
import com.scr.Alertix_DB.Model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository<Categoria,Long> {
    @Query(value = "CALL llenarCategoriaAlerta()", nativeQuery = true)
    List<TipoCategoriasDTO> llenarCategoriaAlerta();
}

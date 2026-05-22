package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.TipoCategoriasDTO;
import com.scr.Alertix_DB.Repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoCategoriasServices {
    @Autowired
    private CategoriasRepository categoriasRepository;

    public List<TipoCategoriasDTO> llenarTipoCategorias() {
        return categoriasRepository.llenarCategoriaAlerta();
    }
}

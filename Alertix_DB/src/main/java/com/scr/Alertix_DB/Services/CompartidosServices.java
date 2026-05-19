package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Model.Compartidos;
import com.scr.Alertix_DB.Repository.CompartidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompartidosServices {
    @Autowired
    private CompartidosRepository compartidosRepository;

    public Integer compartir(Compartidos compa){

        return compartidosRepository.compartir(compa.getIdUsuario().getIdUsuario(),compa.getIdPublicacion().getIdPublicacion());

    }
}

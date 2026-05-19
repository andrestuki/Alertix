package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Model.Seguidores;
import com.scr.Alertix_DB.Repository.SeguidoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeguidoresServices {
    @Autowired
    private SeguidoresRepository seguidoresRepository;

    public Integer seguir(Seguidores seg){
        return seguidoresRepository.seguirUsuario(seg.getIdUsuario().getIdUsuario(),seg.getIdSeguidor().getIdUsuario());
    }


}

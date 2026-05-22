package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.TipoBaneoDTO;
import com.scr.Alertix_DB.Repository.TipoBaneosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoBaneosServices {
    @Autowired
    private TipoBaneosRepository tipoBaneosRepository;

    public List<TipoBaneoDTO> llenarTipoBaneos(){
        return  tipoBaneosRepository.llenarTipoBaneos();
    }
}

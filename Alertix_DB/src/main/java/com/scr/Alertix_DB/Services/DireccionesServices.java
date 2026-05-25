package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Model.Direcciones;
import com.scr.Alertix_DB.Repository.DireccionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionesServices {
    @Autowired
    private DireccionesRepository direccionesRepository;

    public Integer registrarDireccion(Direcciones dir){
        return direccionesRepository.registrarDirecciones(dir.getBarrio(),dir.getDireccion(),
                dir.getPais(),dir.getCiudad(),dir.getDepartamento(),dir.getMunicipio(),dir.getCodigoPostal(),dir.getLatitud(),dir.getLongitud());
    }
}

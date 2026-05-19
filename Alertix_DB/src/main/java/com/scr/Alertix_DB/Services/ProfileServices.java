package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Model.Profile;
import com.scr.Alertix_DB.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServices {

    @Autowired
    private ProfileRepository profileRepository;

    // Solo para buscar el rango y pasárselo al usuario
    public Profile buscarPorId(Long id) {
        return profileRepository.findById(id).orElse(null);
    }
}

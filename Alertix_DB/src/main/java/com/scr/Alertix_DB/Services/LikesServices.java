package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.LikesDetalleDTO;
import com.scr.Alertix_DB.Model.Likes;
import com.scr.Alertix_DB.Repository.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServices {
    @Autowired
    private LikesRepository likesRepository;

    public List<LikesDetalleDTO> darLike(Likes likes){
        return likesRepository.darLike(likes.getIdUsuario().getIdUsuario(),likes.getIdPublicacion().getIdPublicacion());
    }



}

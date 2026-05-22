package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.PublicacionDetalleDTO;
import com.scr.Alertix_DB.Model.Publicaciones;
import com.scr.Alertix_DB.Repository.PublicacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PublicacionesServices {

    @Autowired
    private PublicacionesRepository publicacionesRepository;

    public Integer agragarPublicaciones(Publicaciones publi){
        if (publi.getIdUsuarios() == null) {
            throw new IllegalArgumentException("El usuario de la publicación no puede estar vacío");
        }
        return publicacionesRepository.agregarPublicaciones(publi.getIdUsuarios().getIdUsuario(),publi.getCategorias().getIdCategorias(), publi.getDescripcion(), publi.getIdDireccion().getIdDireccion(), publi.getImg());
    }


    public List<PublicacionDetalleDTO> mostrarPublicacion(){

        return publicacionesRepository.mostrarPublicacion();
    }


    public List<PublicacionDetalleDTO> filtrarPublicacion(Integer idCategoria,Integer idDireccion){
        return publicacionesRepository.filtrarPublicaciones(idCategoria,idDireccion);
    }


    public void eliminarPublicacion(Publicaciones publi){
        publicacionesRepository.eliminarPublicacion(publi.getIdPublicacion(),publi.getIdUsuarios().getIdUsuario());
    }
}

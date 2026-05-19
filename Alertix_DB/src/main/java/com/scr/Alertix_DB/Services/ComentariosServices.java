package com.scr.Alertix_DB.Services;

import com.scr.Alertix_DB.Interface.ComentariosDetalleDTO;
import com.scr.Alertix_DB.Model.Comentarios;
import com.scr.Alertix_DB.Repository.ComentariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComentariosServices {
    @Autowired
    private ComentariosRepository comentariosRepository;

    @Transactional
    public Integer comentar(Comentarios comentarios){
        return comentariosRepository.comentar(comentarios.getIdUsuario().getIdUsuario(),comentarios.getIdPublicacion().getIdPublicacion(),comentarios.getComentario());
    }

    @Transactional
    public Integer guardarRespuesta(Comentarios com) {
        return comentariosRepository.responderComentario(com.getIdUsuario().getIdUsuario(),com.getIdPublicacion().getIdPublicacion(),com.getIdComentarioHija(), com.getComentario());
    }

    @Transactional
    public List<ComentariosDetalleDTO> mostrarComentarios(Long idPublicacion){
        return comentariosRepository.mostrarComentarios(idPublicacion);
    }

    public Integer eliminarComentario( Long idComentario,Long idUsuario){
        return comentariosRepository.eliminarComentario( idComentario, idUsuario);
    }


}

package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.DTO.ComentariosDTO;
import com.scr.alertix.Data.Model.Request.ComentariosRequest;
import com.scr.alertix.Data.Network.ComentarioApi;
import com.scr.alertix.Data.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ComentariosRepository {
    private ComentarioApi comentarioApi;

    public ComentariosRepository(){
        comentarioApi= RetrofitClient.getRetrofitInstance().create(ComentarioApi.class);
    }

    public void obtenerComentarios( Callback<List<ComentariosDTO>> callback,Long idPublicaciones){
        Call<List<ComentariosDTO>> call = comentarioApi.obtenerComentarios(idPublicaciones);
        call.enqueue(callback);
    }

    public void crearComentario(Callback<Void> callback, ComentariosRequest comentario){
        Call<Void> call = comentarioApi.crearComentario(comentario);
        call.enqueue(callback);
    }

    public void responderComentario(ComentariosRequest comentario,Callback<Void> callback ){
        Call<Void> call = comentarioApi.responderComentario(comentario);
        call.enqueue(callback);
    }

    public void eliminarComentario(Long idComentario, Long idUsuario,Callback<Void> callback){
        Call<Void> call = comentarioApi.eliminarComentario(idComentario, idUsuario);
        call.enqueue(callback);
    }
}

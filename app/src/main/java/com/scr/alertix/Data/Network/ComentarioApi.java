package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.DTO.ComentariosDTO;
import com.scr.alertix.Data.Model.Request.ComentariosRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ComentarioApi {
    @GET("mostrar-comentario")
    Call<List<ComentariosDTO>> obtenerComentarios(@Query("idPublicacion") Long idUsuario);

    @POST("comentar")
    Call<Void> crearComentario(@Body ComentariosRequest comentario);

    @POST("Responder")
    Call<Void> responderComentario(@Body ComentariosRequest comentario);

    @DELETE("eliminar-comentario")
    Call<Void> eliminarComentario(@Query("idComentario") Long idComentario, @Query("idUsuario") Long idUsuario);

}

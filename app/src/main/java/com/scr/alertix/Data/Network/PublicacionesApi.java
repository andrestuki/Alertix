package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.DTO.PublicacionDTO;
import com.scr.alertix.Data.Model.Request.PublicacionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PublicacionesApi {

    @GET("mostrarPublicaciones")
    Call<List<PublicacionDTO>> getPublicaciones(@Query("idUsuario") Long idUsuario);

    @POST("AgregarPublicacion")
    Call<Void> crearPublicacion(@Body PublicacionRequest publicacion);
}

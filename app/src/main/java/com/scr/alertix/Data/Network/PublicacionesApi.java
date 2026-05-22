package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.PublicacionDTO;
import com.scr.alertix.Data.Model.PublicacionRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PublicacionesApi {

    @GET("mostrarPublicaciones")
    Call<List<PublicacionDTO>> getPublicaciones();

    @POST("AgregarPublicacion")
    Call<Void> crearPublicacion(@Body PublicacionRequest publicacion);
}

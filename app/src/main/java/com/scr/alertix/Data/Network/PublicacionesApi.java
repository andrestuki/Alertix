package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.PublicacionDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PublicacionesApi {

    @GET("mostrarPublicaciones")
    Call<List<PublicacionDTO>> getPublicaciones();


}

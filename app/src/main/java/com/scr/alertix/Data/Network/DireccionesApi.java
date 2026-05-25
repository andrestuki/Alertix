package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.Request.DireccionesRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DireccionesApi {

    @POST("registrar-direcciones")
    Call<Integer> registrarDireccion(@Body DireccionesRequest direccionesRequest);
}

package com.scr.alertix.Data.Repository;

import retrofit2.Call;

import com.scr.alertix.Data.Model.Request.DireccionesRequest;
import com.scr.alertix.Data.Network.DireccionesApi;
import com.scr.alertix.Data.Network.RetrofitClient;

import java.util.List;

import retrofit2.Callback;

public class DireccionesRepository {
    private DireccionesApi direccionesApi;

    public DireccionesRepository(){
        direccionesApi = RetrofitClient.getRetrofitInstance().create(DireccionesApi.class);
    }

    public void registrarDireccion(DireccionesRequest direccionesRequest, Callback<Integer> callback){
        Call<Integer> call = direccionesApi.registrarDireccion(direccionesRequest);
        call.enqueue(callback);

    }

}

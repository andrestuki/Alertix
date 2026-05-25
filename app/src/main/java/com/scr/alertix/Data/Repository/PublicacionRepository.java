package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.DTO.PublicacionDTO;
import com.scr.alertix.Data.Model.Request.PublicacionRequest;
import com.scr.alertix.Data.Network.PublicacionesApi;
import com.scr.alertix.Data.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PublicacionRepository {
    private PublicacionesApi apiService;

    // Al crear el mesero (Repository), él ya sabe cuál es su cocina (ApiService)
    public PublicacionRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(PublicacionesApi.class);
    }

    // Esta función es la que el Activity va a llamar
    public void obtenerFeed(Callback<List<PublicacionDTO>> callback,Long idUsuario) {
        Call<List<PublicacionDTO>> call = apiService.getPublicaciones(idUsuario);
        call.enqueue(callback);
    }

    public void crearPublicacion(PublicacionRequest publicacion, Callback<Void> callback) {
        apiService.crearPublicacion(publicacion).enqueue(callback);
    }
}


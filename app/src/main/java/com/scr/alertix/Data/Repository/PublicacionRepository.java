package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.PublicacionDTO;
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
    public void obtenerFeed(Callback<List<PublicacionDTO>> callback) {
        Call<List<PublicacionDTO>> call = apiService.getPublicaciones();
        call.enqueue(callback);
    }

    public void crearPublicacion(PublicacionDTO publicacion, Callback<Void> callback) {
        apiService.crearPublicacion(publicacion).enqueue(callback);
    }
}


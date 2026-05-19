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
        // Aquí el mesero hace el pedido a la cocina
        Call<List<PublicacionDTO>> call = apiService.getPublicaciones();

        // El mesero se queda esperando la respuesta (.enqueue)
        // El 'callback' es como el ticket que te avisa cuando la comida llegó
        call.enqueue(callback);
    }
}


package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.DTO.TipoCategoriasDTO;
import com.scr.alertix.Data.Network.CategoriasApi;
import com.scr.alertix.Data.Network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class CategoriasRepository {
    private CategoriasApi apiService;

    public CategoriasRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(CategoriasApi.class);
    }
    public void obtenerFeed(Callback<List<TipoCategoriasDTO>> callback) {
        Call<List<TipoCategoriasDTO>> call = apiService.getCategorias();
        call.enqueue(callback);
    }
}

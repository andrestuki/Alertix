package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.DTO.TipoCategoriasDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriasApi {

    @GET("llenar-tipo-categorias")
    Call<List<TipoCategoriasDTO>> getCategorias();
}

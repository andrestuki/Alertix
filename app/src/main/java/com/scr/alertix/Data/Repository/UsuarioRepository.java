package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.LoginRequest;
import com.scr.alertix.Data.Model.LoginResponse;
import com.scr.alertix.Data.Network.RetrofitClient;
import com.scr.alertix.Data.Network.UsuarioApi;

import retrofit2.Callback;

public class UsuarioRepository {
    private UsuarioApi apiService;

    public UsuarioRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
    }

    public void iniciarSesion(String user, String pass, Callback<LoginResponse> callback) {
        LoginRequest request = new LoginRequest(user, pass);
        apiService.login(request).enqueue(callback);
    }
}

package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.Request.LoginRequest;
import com.scr.alertix.Data.Model.Response.LoginResponse;
import com.scr.alertix.Data.Model.Request.RegisterRequest;
import com.scr.alertix.Data.Network.RetrofitClient;
import com.scr.alertix.Data.Network.UsuarioApi;
import com.scr.alertix.Pojo.Usuario;

import retrofit2.Call;
import retrofit2.Callback;

public class UsuarioRepository {
    private final UsuarioApi apiService;

    public UsuarioRepository() {
        apiService = RetrofitClient.getRetrofitInstance().create(UsuarioApi.class);
    }

    public void iniciarSesion(String user, String pass, Callback<LoginResponse> callback) {
        LoginRequest request = new LoginRequest(user, pass);
        apiService.login(request).enqueue(callback);
    }

    public void registrarUsuario(RegisterRequest request, Callback<Integer> callback) {
        Call<Integer> call= apiService.registrar(request);
        call.enqueue(callback);
    }
}

package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.LoginRequest;
import com.scr.alertix.Data.Model.LoginResponse;
import com.scr.alertix.Data.Model.RegisterRequest;
import com.scr.alertix.Pojo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UsuarioApi {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("usuarios")
    Call<Usuario> registrar(@Body RegisterRequest registerRequest);
}

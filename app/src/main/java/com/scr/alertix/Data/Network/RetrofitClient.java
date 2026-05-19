package com.scr.alertix.Data.Network;

import com.scr.alertix.Utils.Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    // Esta función es la que todos llaman para obtener la conexión
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) { // Si no existe, la creamos (Patrón Singleton)
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://10.0.2.2:8080/api/")// 1. La dirección de tu API
                    .addConverterFactory(GsonConverterFactory.create()) // 2. El traductor (GSON)
                    .build(); // 3. ¡Construir!
        }
        return retrofit;
    }
}

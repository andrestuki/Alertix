package com.scr.alertix.Data.Repository;

import com.scr.alertix.Data.Model.Request.LikeRequest;
import com.scr.alertix.Data.Model.Response.LikesResponse;
import com.scr.alertix.Data.Network.LikesApi;
import com.scr.alertix.Data.Network.RetrofitClient;

import retrofit2.Callback;

public class LikesRepository {
    private LikesApi likesApi;

    public LikesRepository(){
        likesApi= RetrofitClient.getRetrofitInstance().create(LikesApi.class);
    }

    public void darLike(LikeRequest likes , Callback<LikesResponse> callback){
        likesApi.darLike(likes).enqueue(callback);

    }
}

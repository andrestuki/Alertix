package com.scr.alertix.Data.Network;

import com.scr.alertix.Data.Model.Request.LikeRequest;
import com.scr.alertix.Data.Model.Response.LikesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LikesApi {
    @POST("Like")
    Call<LikesResponse> darLike(@Body LikeRequest likes);
}

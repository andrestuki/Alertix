package com.scr.alertix.Data.Model.Response;

import com.google.gson.annotations.SerializedName;

public class LikesResponse {
    @SerializedName("cantidadLikes")
    private Integer cantidadLikes;
    
    @SerializedName("isLiked")
    private int isLiked; // Cambiado a int para recibir 0 o 1 del servidor

    // Getters
    public Integer getCantidadLikes() { 
        return cantidadLikes; 
    }
    
    public boolean getIsLiked() { 
        return isLiked == 1;
    }

    public void setCantidadLikes(Integer cantidadLikes) {
        this.cantidadLikes = cantidadLikes;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }
}

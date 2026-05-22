package com.scr.alertix.Data.Model;

import com.google.gson.annotations.SerializedName;

public class TipoCategoriasDTO {
    @SerializedName("idCategorias")
    private int idCategoria;

    @SerializedName("nombreCategoria")
    private String nombreCategoria;

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
    
    @Override
    public String toString() {
        return nombreCategoria;
    }
}

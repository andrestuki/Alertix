package com.scr.alertix.Data.Model.DTO;

import java.util.ArrayList;

public class ComentarioAgrupado {
    private ComentariosDTO padre;
    private ArrayList<ComentariosDTO> hijos = new ArrayList<>();

    public ComentarioAgrupado(ComentariosDTO padre) {
        this.padre = padre;
    }

    public ComentariosDTO getPadre() { return padre; }
    public ArrayList<ComentariosDTO> getHijos() { return hijos; }
    public void addHijo(ComentariosDTO hijo) { this.hijos.add(hijo); }
}

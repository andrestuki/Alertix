package com.scr.alertix.Data.Model.Response;

import java.util.List;

public class UbicacionResponse {
    private String pais;
    private List<Departamento> departamentos;

    public String getPais() { return pais; }
    public List<Departamento> getDepartamentos() { return departamentos; }

    public static class Departamento {
        private String nombre;
        private List<String> ciudades;

        public String getNombre() { return nombre; }
        public List<String> getCiudades() { return ciudades; }
    }
}

package com.scr.alertix;

public class Publicaciones {
    private int idPublicacion;
    private String descripcion;
    private String nombreUsuario; // Ahora guardamos el nombre real
    private String img;
    private String tipo;
    private String lugar;
    private String fecha;

    public Publicaciones(int idPublicacion, String descripcion, String nombreUsuario, String img, String tipo, String lugar, String fecha) {
        this.idPublicacion = idPublicacion;
        this.descripcion = descripcion;
        this.nombreUsuario = nombreUsuario;
        this.img = img;
        this.tipo = tipo;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getImg() {
        return img;
    }

    public String getTipo() {
        return tipo;
    }

    public String getLugar() {
        return lugar;
    }

    public String getFecha() {
        return fecha;
    }
}

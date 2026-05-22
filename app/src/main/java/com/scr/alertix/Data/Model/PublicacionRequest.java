package com.scr.alertix.Data.Model;

public class PublicacionRequest {
    private String descripcion;
    private String img;
    private UsuarioId idUsuarios;
    private CategoriaId categorias;
    private DireccionId idDireccion;

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setImg(String img) { this.img = img; }
    public void setIdUsuarios(UsuarioId idUsuarios) { this.idUsuarios = idUsuarios; }
    public void setCategorias(CategoriaId categorias) { this.categorias = categorias; }
    public void setIdDireccion(DireccionId idDireccion) { this.idDireccion = idDireccion; }

    public static class UsuarioId {
        private int idUsuario;
        public UsuarioId(int id) { this.idUsuario = id; }
    }

    public static class CategoriaId {
        private int idCategorias;
        public CategoriaId(int id) { this.idCategorias = id; }
    }

    public static class DireccionId {
        private int idDireccion;
        public DireccionId(int id) { this.idDireccion = id; }
    }
}

package com.scr.alertix.Data.Model.Request;

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
        private Long idUsuario;
        public UsuarioId(Long id) { this.idUsuario = id; }
    }

    public static class CategoriaId {
        private Integer idCategorias;
        public CategoriaId(Integer id) { this.idCategorias = id; }
    }

    public static class DireccionId {
        private Integer idDireccion;
        public DireccionId(Integer id) { this.idDireccion = id; }
    }
}

package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="categorias")
@Data
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCategorias;
    @NotBlank
    private String nombreCategoria;

    @OneToMany(mappedBy = "categorias")
    @JsonIgnore
    private List<Publicaciones> publicaciones;
}

package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="publicaciones")
@Data
public class Publicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPublicacion;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "fk_usu_profile")
    private Direcciones idDireccion;

    private String img;

    @ManyToOne
    @JoinColumn(name = "fk_pub_usuario")
    private Usuarios idUsuarios;
    @ManyToOne
    @JoinColumn(name = "fk_pub_idCategoria")
    private Categoria categorias;

    @OneToMany(mappedBy = "idPublicacion")
    @JsonIgnore
    private List<Comentarios> comentarios;

    @OneToMany(mappedBy = "idPublicacion")
    @JsonIgnore
    private List<Likes> likes;

    @OneToMany(mappedBy = "idPublicacion")
    @JsonIgnore
    private List<Reportes> reportes;

    @OneToMany(mappedBy = "idPublicacion")
    @JsonIgnore
    private List<Compartidos> compartidos;


}

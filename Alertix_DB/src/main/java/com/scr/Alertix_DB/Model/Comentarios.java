package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Data
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComentiarios;

    private Long idComentarioHija=null;

    @ManyToOne
    @JoinColumn(name = "fk_com_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_com_publicacion")
    private Publicaciones idPublicacion;

    @Column(updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fecha;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String comentario;

}

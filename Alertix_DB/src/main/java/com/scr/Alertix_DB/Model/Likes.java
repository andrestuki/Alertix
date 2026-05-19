package com.scr.Alertix_DB.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "likes")
@Data
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLikes;

    @ManyToOne
    @JoinColumn(name = "fk_lik_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_lik_publicacion")
    private Publicaciones idPublicacion;

}

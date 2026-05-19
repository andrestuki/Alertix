package com.scr.Alertix_DB.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "seguidores")
@Data
public class Seguidores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeguidores;

    @ManyToOne
    @JoinColumn(name="fk_seg_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_seg_seguidor")
    private Usuarios idSeguidor;

}

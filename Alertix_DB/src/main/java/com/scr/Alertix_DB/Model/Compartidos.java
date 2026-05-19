package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "compartidos")
@Data
public class Compartidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCompartido;

    @ManyToOne
    @JoinColumn(name = "fk_compa_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_compa_publicacion")
    private Publicaciones idPublicacion;

}

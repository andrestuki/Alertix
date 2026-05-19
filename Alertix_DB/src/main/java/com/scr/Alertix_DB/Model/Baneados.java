package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "baneados")
@Data
public class Baneados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBaneados;

    @ManyToOne
    @JoinColumn(name = "fk_ban_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fK_ban_baneado")
    private Usuarios idBaneado;

    @ManyToOne
    @JoinColumn(name = "fk_ban_tipoBaneo")
    private TipoBaneos idTipoBaneos;


    private LocalDateTime fechaBaneo;

}

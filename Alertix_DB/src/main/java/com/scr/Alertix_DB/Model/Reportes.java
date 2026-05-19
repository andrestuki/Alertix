package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
public class Reportes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReportes;

    @ManyToOne
    @JoinColumn(name = "fk_rep_usuario")
    private Usuarios idUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_rep_reportado")
    private Usuarios idReportado;

    @ManyToOne
    @JoinColumn(name = "fk_rep_publicacion")
    private Publicaciones idPublicacion;

    @Column(updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaReporte;

    @ManyToOne
    @JoinColumn(name = "fk_rep_tipoReportes")
    private TipoReportes idTipoReportes;


}

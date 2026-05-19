package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tipoReportes")
@Data
public class TipoReportes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoReportes;

    @NotBlank
    private String tipoReportes;

    @OneToMany(mappedBy = "idTipoReportes")
    @JsonIgnore
    private List<Reportes> Reportes;
}

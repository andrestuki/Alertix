package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "baneados")
@Data
public class TipoBaneos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTipoBaneos;

    private String tipoBaneos;

    private int duracionBaneo;

    @OneToMany(mappedBy = "idTipoBaneos")
    @JsonIgnore
    private List<Baneados> baneados;
}

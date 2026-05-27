package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "direcciones")
@Data
public class Direcciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    private String barrio;

    private String direccion;

    private String pais;

    private String ciudad;

    private String departamento;


    private String codigoPostal;

    private double latitud;

    private double longitud;

    @OneToMany(mappedBy = "idDireccion")
    @JsonIgnore
    private List<Usuarios> usuarios;

    @OneToMany(mappedBy = "idDireccion")
    @JsonIgnore
    private List<Publicaciones> publicaciones;
}

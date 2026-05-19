package com.scr.Alertix_DB.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.List;

@Entity
@Table(name = "perfiles")
@Data
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProfile;

    @Column(unique = true, nullable = false)
    private String nombreProfile;

    @OneToMany(mappedBy = "profile")
    @JsonIgnore
    private List<Usuarios> usuarios;
}

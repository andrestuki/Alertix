package com.scr.Alertix_DB.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String usuarioNombre;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El género es obligatorio")
    private String generoUsuario;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @Column(updatable = false, insertable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaUsuarioCreado;

    @ManyToOne
    @JoinColumn(name = "fk_usu_direccion")
    private Direcciones idDireccion;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contraseniaUsuario;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Formato de correo inválido")
    private String correoUsuario;

    private String imgPerfil;

    private String usuarioEstado="ACTIVO";

    @ManyToOne
    @JoinColumn(name = "fk_usu_profile")
    private Profile profile;

    @OneToMany(mappedBy = "idUsuarios")
    @JsonIgnore
    private List<Publicaciones> publicaciones;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Seguidores> seguidoresU;

    @OneToMany(mappedBy = "idSeguidor")
    @JsonIgnore
    private List<Seguidores> seguidoresS;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Comentarios> comentarios;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Reportes> reportes;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Baneados> baneados;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Likes> likes;

    @OneToMany(mappedBy = "idUsuario")
    @JsonIgnore
    private List<Compartidos> compartidos;

    @OneToMany(mappedBy = "idReportado")
    @JsonIgnore
    private List<Reportes> reportess;

    @OneToMany(mappedBy = "idBaneado")
    @JsonIgnore
    private List<Baneados> baneados2;


}

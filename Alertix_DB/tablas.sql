CREATE TABLE perfiles(
    idProfile INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombreProfile varchar(100)
);

CREATE TABLE usuarios (
    idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
    usuarioNombre varchar(100),
    nombre varchar(100),
    apellido varchar(100),
    generoUsuario varchar(100),
    fechaNacimiento datetime,
    fechaUsuarioCreado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    direccion varchar(200),
    contraseniaUsuario varchar(100),
    correoUsuario varchar(100),
    imgPerfil varchar(255),
    usuarioEstado varchar(100),
    idProfile int,
    CONSTRAINT fk_usu_profile FOREIGN KEY(idProfile) REFERENCES perfiles(idProfile) ON DELETE CASCADE
);

CREATE TABLE seguidores(
    idSeguidores INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer,
    idSeguidor integer,
    fechaSeguimiento datetime,
    UNIQUE KEY unique_seguimiento (idUsuario, idSeguidor),
    CONSTRAINT fk_seg_usuario FOREIGN KEY(idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_seg_seguidor FOREIGN KEY(idSeguidor) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);

CREATE TABLE categorias(
    idCategorias INT PRIMARY KEY AUTO_INCREMENT,
    nombreCategoria varchar(200)
); 

CREATE TABLE publicaciones (
    idPublicacion INTEGER PRIMARY KEY AUTO_INCREMENT, 
    idUsuario INTEGER,
    idCategorias int,
    descripcion text,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
    direccion varchar(200),
    img varchar(300),
    constraint fk_pub_idCategoria foreign key(idCategorias) references categorias(idCategorias) ON DELETE CASCADE,
    CONSTRAINT fk_pub_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);

CREATE TABLE comentarios(
    idComentarios INTEGER PRIMARY KEY AUTO_INCREMENT,
    idComentarioHijo INTEGER DEFAULT NULL,
    idUsuario integer,
    idPublicacion integer,
    fechaComentario datetime,
    comentario text,
    CONSTRAINT fk_com_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_com_publicacion FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion) ON DELETE CASCADE 
);

CREATE TABLE tipoReportes(
	idTipoReportes INTEGER PRIMARY KEY AUTO_INCREMENT,
	tipoReportes varchar(200)
 );

CREATE TABLE reportes(
    idReportes INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer,
    idReportado integer,
    idPublicacion integer,
    idTipoReportes integer,
    fechaReporte datetime,
	UNIQUE KEY unique_seguimiento (idUsuario, idReportado),
    CONSTRAINT fk_rep_tipoReportes foreign key(idTipoReportes) references tipoReportes(idTipoReportes) on delete cascade,
    CONSTRAINT fk_rep_reportado FOREIGN KEY(idReportado) REFERENCES usuarios(idUsuario) ON DELETE CASCADE ,
    CONSTRAINT fk_rep_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_rep_publicacion FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion) ON DELETE CASCADE
);

CREATE TABLE tipoBaneos(
	idTipoBaneos INTEGER PRIMARY KEY AUTO_INCREMENT,
	tipoBaneos varchar(200),
    duracionBaneo int
);

CREATE TABLE baneados(
    idBaneados INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer,
    idBaneado integer,
    idTipoBaneos integer,
    fechaBaneo datetime,
    fechaFinBaneo datetime,
    CONSTRAINT fk_ban_tipoBaneo foreign key(idTipoBaneos) references tipoBaneos(idTipoBaneos) on delete cascade, 
    CONSTRAINT fK_ban_baneado foreign key (idBaneado) references usuarios(idUsuario) on delete cascade,
    CONSTRAINT fk_ban_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE
);

CREATE TABLE likes(
    idLikes INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer ,
    idPublicacion integer,
    fechaLike datetime,
    CONSTRAINT fk_lik_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_lik_publicacion FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion) ON DELETE CASCADE
);

CREATE TABLE compartidos(
    idCompartidos INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer,
    idPublicacion integer,
    fechaCompartido datetime,
    CONSTRAINT fk_compa_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_compa_publicacion FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion) ON DELETE CASCADE
);
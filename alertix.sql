DROP DATABASE IF EXISTS alertix;
CREATE DATABASE alertix;
USE alertix;

CREATE TABLE perfiles(
    idProfile INTEGER PRIMARY KEY AUTO_INCREMENT,
    nombreProfile varchar(100)
);

CREATE TABLE direcciones(
    idDireccion INTEGER PRIMARY KEY AUTO_INCREMENT,
    barrio varchar(100),
    direccion varchar(200),
    pais varchar(100), 
    ciudad varchar(100), 
    departamento varchar(100),  
    municipio varchar(100), 
    codigoPostal varchar(20)
);

CREATE TABLE usuarios (
    idUsuario INTEGER PRIMARY KEY AUTO_INCREMENT,
    usuarioNombre varchar(100),
    nombre varchar(100),
    apellido varchar(100),
    generoUsuario varchar(100),
    fechaNacimiento datetime,
    fechaUsuarioCreado TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    idDireccion int,
    contraseniaUsuario varchar(100),
    correoUsuario varchar(100),
    imgPerfil LONGTEXT,
    usuarioEstado varchar(100),
    idProfile int,
    CONSTRAINT fk_usu_direccion FOREIGN KEY(idDireccion) REFERENCES direcciones(idDireccion) ON DELETE SET NULL,
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
    idDireccion int,
    img LONGTEXT,
    constraint fk_pub_idCategoria foreign key(idCategorias) references categorias(idCategorias) ON DELETE CASCADE,
    CONSTRAINT fk_pub_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_pub_direccion FOREIGN KEY (idDireccion) REFERENCES direcciones(idDireccion) ON DELETE SET NULL
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

CREATE TABLE tipoBaneos(
	idTipoBaneos INTEGER PRIMARY KEY AUTO_INCREMENT,
	tipoBaneos varchar(200),
    duracionBaneo int
);

CREATE TABLE reportes(
    idReportes INTEGER PRIMARY KEY AUTO_INCREMENT,
    idUsuario integer,
    idReportado integer,
    idPublicacion integer,
    idTipoReportes integer,
    fechaReporte datetime,
	UNIQUE KEY unique_reportes (idUsuario, idReportado),
    CONSTRAINT fk_rep_tipoReportes foreign key(idTipoReportes) references tipoReportes(idTipoReportes) on delete cascade,
    CONSTRAINT fk_rep_reportado FOREIGN KEY(idReportado) REFERENCES usuarios(idUsuario) ON DELETE CASCADE ,
    CONSTRAINT fk_rep_usuario FOREIGN KEY (idUsuario) REFERENCES usuarios(idUsuario) ON DELETE CASCADE,
    CONSTRAINT fk_rep_publicacion FOREIGN KEY (idPublicacion) REFERENCES publicaciones(idPublicacion) ON DELETE CASCADE
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

DELIMITER //

CREATE PROCEDURE llenarCategoriaAlerta()
BEGIN
    SELECT idCategorias, nombreCategoria FROM categorias;
END;//

-- USUARIOS:

CREATE PROCEDURE registrarUsuario(
    p_usuarioNombre varchar(100),
    p_nombre varchar(100),
    p_apellido varchar(100),
    p_generoUsuario varchar(100),
    p_fechaNacimiento datetime,
    p_idDireccion integer,
    p_contraseniaUsuario varchar(200),
    p_correoUsuario varchar(100),
    p_imgPerfil LONGTEXT
) 
BEGIN
    DECLARE conteo INT;
    SELECT COUNT(*) INTO conteo FROM usuarios WHERE usuarioNombre = p_usuarioNombre OR correoUsuario = p_correoUsuario;
    
    IF conteo = 0 THEN
        INSERT INTO usuarios(usuarioNombre, nombre, apellido, generoUsuario, fechaNacimiento, idDireccion, contraseniaUsuario, correoUsuario, imgPerfil, usuarioEstado, idProfile) 
        VALUES(p_usuarioNombre, p_nombre, p_apellido, p_generoUsuario, p_fechaNacimiento, p_idDireccion, p_contraseniaUsuario, p_correoUsuario, p_imgPerfil, 'activo', 2);
    ELSE
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El usuario o el correo ya estan registrados';
    END IF;
END;//

CREATE PROCEDURE validarLogin(p_usuarioNombre VARCHAR(45), p_contrasenia VARCHAR(45))
BEGIN
    DECLARE v_idUsuario INT;
    DECLARE v_idPerfil INT;

    SELECT idUsuario, idProfile INTO v_idUsuario, v_idPerfil
    FROM usuarios
    WHERE usuarioNombre = p_usuarioNombre AND contraseniaUsuario = p_contrasenia
    LIMIT 1;

    IF v_idUsuario IS NOT NULL THEN
        SELECT v_idUsuario AS idUsuario, v_idPerfil AS idProfile;
    ELSE
        SELECT 0 AS idUsuario,  0 AS idProfile;
    END IF;
END;//

CREATE PROCEDURE mostrarUsuario(p_idUsuario integer)
BEGIN
    DECLARE total_likes INT;
    DECLARE total_seguidores INT;
    DECLARE total_seguidos INT;

    SELECT COUNT(*) INTO total_likes FROM likes l INNER JOIN publicaciones pub ON l.idPublicacion = pub.idPublicacion WHERE pub.idUsuario = p_idUsuario;

    SELECT COUNT(*) INTO total_seguidores FROM seguidores WHERE idUsuario = p_idUsuario;
    SELECT COUNT(*) INTO total_seguidos FROM seguidores WHERE idSeguidor = p_idUsuario;

    SELECT 
        u.idUsuario, 
        u.usuarioNombre, 
        u.imgPerfil, 
        p.idPublicacion, 
        p.descripcion, 
        d.pais,
        d.ciudad, 
        p.img AS imgPublicacion, 
        p.fecha, 
        c.nombreCategoria,
        total_likes AS totalLikesRecibidos,
        total_seguidores AS cantidadSeguidores,
        total_seguidos AS cantidadSeguidos
    FROM usuarios u
    LEFT JOIN direcciones d ON u.idDireccion = d.idDireccion
    LEFT JOIN publicaciones p ON u.idUsuario = p.idUsuario
    LEFT JOIN categorias c ON p.idCategorias = c.idCategorias
    WHERE u.idUsuario = p_idUsuario
    ORDER BY p.fecha DESC;
END;//

-- PUBLICACIONES:

CREATE PROCEDURE agregarPublicaciones(
    IN p_idUsuario INT,
    IN p_idCategorias INT,
    IN p_descripcion TEXT,
    IN p_idDireccion INT, 
    IN p_img LONGTEXT
)
BEGIN
    -- Corregimos quitando el p_descripcion repetido
    INSERT INTO publicaciones(idUsuario, idCategorias, descripcion, fecha, idDireccion, img)
    VALUES(p_idUsuario, p_idCategorias, p_descripcion, NOW(), p_idDireccion, p_img);
    
    -- Te recomiendo agregar esto para que Java reciba el ID de lo que creaste
    SELECT LAST_INSERT_ID() AS idNuevaPublicacion;
END;//

CREATE PROCEDURE mostrarPublicacion()
BEGIN
    SELECT 
        u.usuarioNombre,
        u.imgPerfil,
        p.idPublicacion,
        p.descripcion,
        d.barrio, 
        p.img AS imgPublicacion,
        p.fecha,
        c.nombreCategoria,
        (SELECT COUNT(*) FROM likes l WHERE l.idPublicacion = p.idPublicacion) AS cantidadLikes,
        (SELECT COUNT(*) FROM comentarios cm WHERE cm.idPublicacion = p.idPublicacion) AS cantidadComentarios,
        (SELECT COUNT(*) FROM compartidos cp WHERE cp.idPublicacion = p.idPublicacion) AS cantidadCompartidos
    FROM usuarios u  
    INNER JOIN publicaciones p ON u.idUsuario = p.idUsuario 
    INNER JOIN categorias c ON p.idCategorias = c.idCategorias
    INNER JOIN direcciones d ON p.idDireccion = d.idDireccion 
    ORDER BY p.fecha DESC;
END;//


CREATE PROCEDURE filtrarPublicaciones(
    p_idCategorias INT, 
    p_direccion VARCHAR(200)
)
BEGIN
    SELECT 
        u.usuarioNombre,
        u.imgPerfil,
        p.idPublicacion,
        p.descripcion,
        d.barrio, 
        p.img AS imgPublicacion,
        p.fecha,
        c.nombreCategoria,
        (SELECT COUNT(*) FROM likes l WHERE l.idPublicacion = p.idPublicacion) AS cantidadLikes,
        (SELECT COUNT(*) FROM comentarios cm WHERE cm.idPublicacion = p.idPublicacion) AS cantidadComentarios,
        (SELECT COUNT(*) FROM compartidos cp WHERE cp.idPublicacion = p.idPublicacion) AS cantidadCompartidos 
    FROM usuarios u  
    INNER JOIN publicaciones p ON u.idUsuario = p.idUsuario 
    INNER JOIN categorias c ON p.idCategorias = c.idCategorias  
    INNER JOIN direcciones d ON p.idDireccion = d.idDireccion 
    WHERE (p_idCategorias IS NULL OR p_idCategorias = 0 OR p.idCategorias = p_idCategorias)
    AND (p_direccion IS NULL OR p_direccion = '' OR d.barrio LIKE CONCAT('%', p_direccion, '%'))
    ORDER BY p.fecha DESC;
END;//

CREATE PROCEDURE eliminarPublicacion(p_idPublicacion integer,p_idUsuario integer)
BEGIN
    DECLARE v_idProfile INT;

    SELECT idProfile INTO v_idProfile FROM usuarios WHERE idUsuario = p_idUsuario;

    DELETE FROM publicaciones WHERE idPublicacion = p_idPublicacion AND (idUsuario = p_idUsuario OR v_idProfile = 1);

END;//


-- COMENTARIOS:

CREATE PROCEDURE comentar(p_idUsuario integer,p_idPublicacion integer,p_comentario text)
BEGIN
	DECLARE u_usuario integer;
    DECLARE u_publicacion integer;
    
    select idUsuario into u_usuario from usuarios where idUsuario=p_idUsuario;
    select idPublicacion into u_publicacion from publicaciones where idPublicacion=p_idPublicacion;
    if u_usuario=p_idUsuario then
		if u_publicacion=p_idPublicacion then
			insert into comentarios(idUsuario,idComentarioHijo,idPublicacion,comentario,fechaComentario)values(p_idUsuario,null,p_idPublicacion,p_comentario,now());
            SELECT COUNT(*) AS total FROM comentarios WHERE idPublicacion = p_idPublicacion;
        else
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Error';
        end if;
    else
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Error';
    end if;
	
END;//


CREATE PROCEDURE responderComentario(
    p_idUsuario INTEGER,
    p_idPublicacion INTEGER,
    p_idComentarioPadre INTEGER,
    p_comentario TEXT
) 
BEGIN
    -- Corregimos el INSERT añadiendo la columna 'comentario'
    INSERT INTO comentarios(idUsuario, idPublicacion, idComentarioHijo, comentario, fechaComentario) 
    VALUES (p_idUsuario, p_idPublicacion, p_idComentarioPadre, p_comentario, NOW());

    -- Dejamos SOLO el conteo para que Java reciba el total
    SELECT COUNT(*) AS total FROM comentarios WHERE idPublicacion = p_idPublicacion;
END;//

CREATE PROCEDURE mostrarComentarios(p_idPublicacion integer)
BEGIN
    SELECT c.idComentarios, c.idComentarioHijo, c.idUsuario, u.usuarioNombre, u.imgPerfil, c.comentario, c.fechaComentario FROM comentarios c
    INNER JOIN usuarios u ON c.idUsuario = u.idUsuario WHERE c.idPublicacion = p_idPublicacion ORDER BY COALESCE(c.idComentarioHijo, c.idComentarios), c.fechaComentario ASC;
END;//


CREATE PROCEDURE eliminarComentario(
    IN p_idComentario INTEGER, 
    IN p_idUsuario INTEGER
)
BEGIN
    DECLARE v_idProfile INT;
    DECLARE v_idPublicacion INT;

    SELECT idProfile INTO v_idProfile FROM usuarios WHERE idUsuario = p_idUsuario;
    
    SELECT idPublicacion INTO v_idPublicacion FROM comentarios WHERE idComentarios = p_idComentario;
    
    DELETE FROM comentarios WHERE idComentarios = p_idComentario AND (idUsuario = p_idUsuario OR v_idProfile = 1);
    
    SELECT COUNT(*) AS total FROM comentarios WHERE idPublicacion = v_idPublicacion;
END;//

-- LIKES:

CREATE PROCEDURE darLike(
    IN p_idUsuario INT,
    IN p_idPublicacion INT
)
BEGIN
    IF EXISTS (SELECT 1 FROM likes WHERE idUsuario = p_idUsuario AND idPublicacion = p_idPublicacion) THEN
        DELETE FROM likes WHERE idUsuario = p_idUsuario AND idPublicacion = p_idPublicacion;
    ELSE
     
        INSERT INTO likes(idUsuario, idPublicacion, fechaLike)
        VALUES(p_idUsuario, p_idPublicacion, NOW());
    END IF;

    -- Devolvemos el total de likes actualizado para la APK
    SELECT COUNT(*) AS total FROM likes WHERE idPublicacion = p_idPublicacion;
END;//

-- SEGUIR:

CREATE PROCEDURE seguirUsuario(
    IN p_idUsuario INT, 
    IN p_idSeguidor INT
)
BEGIN

    IF p_idUsuario != p_idSeguidor THEN
        IF NOT EXISTS (SELECT 1 FROM seguidores WHERE idUsuario = p_idUsuario AND idSeguidor = p_idSeguidor) THEN
            INSERT INTO seguidores (idUsuario, idSeguidor) 
            VALUES (p_idUsuario, p_idSeguidor);
        ELSE
            DELETE FROM seguidores WHERE idUsuario = p_idUsuario AND idSeguidor = p_idSeguidor;
        END IF;
    END IF;
    SELECT COUNT(*) AS total_seguidores FROM seguidores WHERE idUsuario = p_idUsuario;
END;//



-- COMPARTIR:

CREATE PROCEDURE compartir(p_idUsuario integer,p_idPublicacion integer) 
BEGIN
	DECLARE u_usuario integer;
    DECLARE u_publicacion integer;
    
    select idUsuario into u_usuario from usuarios where idUsuario=p_idUsuario;
    select idPublicacion into u_publicacion from publicaciones where idPublicacion=p_idPublicacion;
    if u_usuario=p_idUsuario then
		if u_publicacion=p_idPublicacion then
			 insert into compartidos(idUsuario,idPublicacion,fechaCompartido)values(p_idUsuario,p_idPublicacion,now());
             SELECT COUNT(*) AS total FROM compartidos WHERE idPublicacion = p_idPublicacion;
        else
			SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Error';
        end if;
    else
		SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'Error';
    end if;
END;//


-- REPORTES:

CREATE PROCEDURE reportarUsuario(p_idUsuario integer,p_idReportado integer,p_idTipoReportes integer) 
BEGIN
	declare total_reportes integer;
    declare duracion int;
    declare v_tipoBaneo varchar(200);
    declare fecha_fin datetime;
    
	INSERT INTO reportes(idUsuario, idReportado, idPublicacion, idTipoReportes, fechaReporte) 
    VALUES(p_idUsuario, p_idReportado, NULL, p_idTipoReportes, NOW());

    SELECT COUNT(*) INTO total_reportes FROM reportes WHERE idReportado = p_idReportado;
    IF total_reportes >= 10 THEN

        select duracion into duracion from tipoReportes where idTipoReportes=p_idTipoReportes;
        SET fecha_fin = DATE_ADD(CURDATE(), INTERVAL duracion DAY);
        insert into baneados(idUsuario, idBaneado, idTipoBaneos, fechaBaneo, fechaFinBaneo) values(p_idUsuario,p_idReportado,1,now(),fecha_fin);

        select tipoBaneos into v_tipoBaneo from tipoBaneos where idTipoBaneos=1;
        update usuarios set usuarioEstado=v_tipoBaneo where idUsuario=p_idReportado;
    END IF;
    
    SELECT total_reportes;
END;//


CREATE PROCEDURE reportarPublicacion(p_idUsuario integer,p_idPublicacion integer,p_idTipoReportes integer)
BEGIN
	declare total_reportes integer;
    
	INSERT INTO reportes (idUsuario,idReportado,idPublicacion,idTipoReportes,fechaReporte)
    VALUES(p_idUsuario,null,p_idPublicacion,p_idTipoReportes,now());
    
    select count(*) into total_reportes from reportes where idPublicacion=p_idPublicacion;

    IF total_reportes >= 10 THEN
        DELETE FROM publicaciones WHERE idPublicacion = p_idPublicacion;
    
    END IF;
    
    select total_reportes;
END;//

CREATE PROCEDURE mostrarUsuariosReportados()
BEGIN
    SELECT  u_mal.idUsuario AS idReportado, u_mal.usuarioNombre AS nombreReportado,u_bien.idUsuario AS idDenunciante,u_bien.usuarioNombre AS nombreDenunciante,r.fechaReporte
    FROM reportes r
    INNER JOIN usuarios u_mal ON r.idReportado = u_mal.idUsuario
    INNER JOIN usuarios u_bien ON r.idUsuario = u_bien.idUsuario
    ORDER BY r.fechaReporte DESC;
END;//

CREATE PROCEDURE mostrarPublicacionesReportadas()
BEGIN
    SELECT 
        p.idPublicacion, 
        p.descripcion, 
        d.direccion AS direccion, -- Ahora lo traemos de la tabla direcciones
        p.img AS imgPublicacion, 
        p.fecha, 
        c.nombreCategoria,
        u_autor.usuarioNombre AS autorPublicacion,
        u_autor.imgPerfil AS fotoAutor,
        COUNT(r.idReportes) AS cantidadReportes,
        GROUP_CONCAT(u_denunciante.usuarioNombre SEPARATOR ', ') AS nombresDenunciantes
    FROM publicaciones p
    INNER JOIN categorias c ON p.idCategorias = c.idCategorias
    INNER JOIN usuarios u_autor ON p.idUsuario = u_autor.idUsuario
    INNER JOIN direcciones d ON p.idDireccion = d.idDireccion 
    INNER JOIN reportes r ON p.idPublicacion = r.idPublicacion
    INNER JOIN usuarios u_denunciante ON r.idUsuario = u_denunciante.idUsuario
    GROUP BY 
        p.idPublicacion, 
        p.descripcion, 
        d.direccion, -- Agregado al Group By
        p.img, 
        p.fecha, 
        c.nombreCategoria, 
        u_autor.usuarioNombre, 
        u_autor.imgPerfil
    HAVING COUNT(r.idReportes) >= 1
    ORDER BY cantidadReportes DESC;
END;//

-- BANEOS:

CREATE PROCEDURE banearUsuario(p_idUsuario integer,p_baneado integer,p_idTipoBaneos integer)
BEGIN
	declare u_idProfile integer;
    declare duracion int;
    declare v_tipoBaneo varchar(200);
    declare fecha_fin datetime;
    
    select idProfile into u_idProfile from usuarios where idUsuario=p_idUsuario;
    
    if u_idProfile=1 then
        select duracion into duracion from tipoBaneos where idTipoBaneos=p_idTipoBaneos;

        SET fecha_fin = DATE_ADD(CURDATE(), INTERVAL duracion DAY);
         
        insert into baneados(idUsuario,idBaneado,idTipoBaneos,fechaBaneo,fechaFinBaneo)values(p_idUsuario,p_baneado,p_idTipoBaneos,now(),fecha_fin);

        select tipoBaneos into v_tipoBaneo from tipoBaneos where idTipoBaneos=p_idTipoBaneos;

        update usuarios set usuarioEstado=v_tipoBaneo where idUsuario=p_baneado;
    else
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No tienes permisos para banear';
    end if;
END;//



CREATE PROCEDURE mostrarBaneaos()
BEGIN
    SELECT b.idBaneados, u.usuarioNombre AS usuarioBaneado, ub.usuarioNombre AS usuarioQueBaneo, tb.tipoBaneos, b.fechaBaneo
    FROM baneados b
    LEFT JOIN usuarios u ON b.idBaneado = u.idUsuario
    LEFT JOIN usuarios ub ON b.idUsuario = ub.idUsuario
    LEFT JOIN tipoBaneos tb ON b.idTipoBaneos = tb.idTipoBaneos
    ORDER BY b.fechaBaneo DESC;
END;//


DELIMITER ;

INSERT  INTO perfiles (idProfile, nombreProfile) VALUES (1,'ADMIN');
INSERT INTO perfiles (idProfile, nombreProfile) VALUES (2, 'USUARIO');


-- Inserts para la tabla direcciones (data.sql)
INSERT  INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (1, 'El Prado', 'Carrera 4 # 22-10', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470001');

INSERT  INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (2, 'Bureche', 'Calle Principal Bureche School', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470006');

INSERT  INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (3, 'Centro Histórico', 'Calle 15 # 3-45', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470001');

INSERT INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (4, 'Ciudad Jardín', 'Avenida Principal 123', 'Colombia', 'Cali', 'Valle del Cauca', 'Cali', '760002');

INSERT  INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (5, 'Chapinero', 'Calle 67 # 11-00', 'Colombia', 'Bogotá', 'Cundinamarca', 'Bogotá', '110231');
-- Categorías para las publicaciones de Alertix

INSERT INTO categorias (idCategorias, nombreCategoria) VALUES
(1, 'Seguridad'),
(2, 'Infraestructura'),
(3, 'Eventos'),
(4, 'Emergencias');

select * from categorias;

-- Tipos de Reporte
INSERT INTO tipoReportes (idTipoReportes, tipoReportes) VALUES
(1, 'Contenido Inapropiado'),
(2, 'Spam'),
(3, 'Acoso'),
(4, 'Información Falsa');

-- Tipos de Baneo y duraciones (en días)
INSERT  INTO tipoBaneos (idTipoBaneos, tipoBaneos, duracionBaneo) VALUES
(1, 'Advertencia', 3),
(2, 'Suspensión Temporal', 7),
(3, 'Baneo Permanente', 365);

INSERT  INTO usuarios (idUsuario, usuarioNombre, nombre, apellido, generoUsuario, fechaNacimiento, idDireccion, contraseniaUsuario, correoUsuario, imgPerfil, usuarioEstado, idProfile) VALUES
(1, 'admin_central', 'Carlos', 'Admin', 'Masculino', '1990-01-01', 1, 'admin123', 'admin@alertix.com', 'admin_pic.png', 'ACTIVO', 1),
(2, 'juan_dev', 'Juan', 'Perez', 'Masculino', '1995-05-15', 2, 'juan123', 'juan@mail.com', 'juan_dev.jpg', 'ACTIVO', 2),
(3, 'maria_sos', 'Maria', 'Gomez', 'Femenino', '1998-10-20', 3, 'maria123', 'maria@mail.com', 'maria_user.png', 'ACTIVO', 2);


-- Publicaciones (Corregido para usar idDireccion)
INSERT INTO publicaciones (idPublicacion, idUsuario, idCategorias, descripcion, idDireccion, img) VALUES
(1, 2, 1, 'Robo detectado cerca del parque, tengan cuidado.', 1, 'robo_foto.jpg'),
(2, 3, 2, 'Gran hueco en la vía principal, afecta el tráfico.', 3, 'hueco_via.png');

-- Comentarios
INSERT  INTO comentarios (idComentarios, idUsuario, idPublicacion, fechaComentario, comentario) VALUES
(1, 3, 1, NOW(), 'Gracias por avisar, por allá paso siempre.'),
(2, 1, 2, NOW(), 'Reportado a la secretaría de movilidad.');

-- Likes
INSERT  INTO likes (idLikes, idUsuario, idPublicacion, fechaLike) VALUES
(1, 1, 1, NOW()),
(2, 3, 1, NOW());

select * from usuarios;

select * from reportes;

select * from publicaciones;

DESCRIBE publicaciones;


                
                
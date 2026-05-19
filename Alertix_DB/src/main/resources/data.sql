INSERT IGNORE INTO perfiles (idProfile, nombreProfile) VALUES (1,'ADMIN');
INSERT IGNORE INTO perfiles (idProfile, nombreProfile) VALUES (2, 'USUARIO');


-- Inserts para la tabla direcciones (data.sql)
INSERT IGNORE INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (1, 'El Prado', 'Carrera 4 # 22-10', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470001');

INSERT IGNORE INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (2, 'Bureche', 'Calle Principal Bureche School', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470006');

INSERT IGNORE INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (3, 'Centro Histórico', 'Calle 15 # 3-45', 'Colombia', 'Santa Marta', 'Magdalena', 'Santa Marta', '470001');

INSERT IGNORE INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (4, 'Ciudad Jardín', 'Avenida Principal 123', 'Colombia', 'Cali', 'Valle del Cauca', 'Cali', '760002');

INSERT IGNORE INTO direcciones (idDireccion, barrio, direccion, pais, ciudad, departamento, municipio, codigoPostal)
VALUES (5, 'Chapinero', 'Calle 67 # 11-00', 'Colombia', 'Bogotá', 'Cundinamarca', 'Bogotá', '110231');
-- Categorías para las publicaciones de Alertix

INSERT IGNORE INTO categorias (idCategorias, nombreCategoria) VALUES
(1, 'Seguridad'),
(2, 'Infraestructura'),
(3, 'Eventos'),
(4, 'Emergencias');

-- Tipos de Reporte
INSERT IGNORE INTO tipoReportes (idTipoReportes, tipoReportes) VALUES
(1, 'Contenido Inapropiado'),
(2, 'Spam'),
(3, 'Acoso'),
(4, 'Información Falsa');

-- Tipos de Baneo y duraciones (en días)
INSERT IGNORE INTO tipoBaneos (idTipoBaneos, tipoBaneos, duracionBaneo) VALUES
(1, 'Advertencia', 3),
(2, 'Suspensión Temporal', 7),
(3, 'Baneo Permanente', 365);

INSERT IGNORE INTO usuarios (idUsuario, usuarioNombre, nombre, apellido, generoUsuario, fechaNacimiento, idDireccion, contraseniaUsuario, correoUsuario, imgPerfil, usuarioEstado, idProfile) VALUES
(1, 'admin_central', 'Carlos', 'Admin', 'Masculino', '1990-01-01', 1, 'admin123', 'admin@alertix.com', 'admin_pic.png', 'ACTIVO', 1),
(2, 'juan_dev', 'Juan', 'Perez', 'Masculino', '1995-05-15', 2, 'juan123', 'juan@mail.com', 'juan_dev.jpg', 'ACTIVO', 2),
(3, 'maria_sos', 'Maria', 'Gomez', 'Femenino', '1998-10-20', 3, 'maria123', 'maria@mail.com', 'maria_user.png', 'ACTIVO', 2);


-- Publicaciones (Corregido para usar idDireccion)
INSERT IGNORE INTO publicaciones (idPublicacion, idUsuario, idCategorias, descripcion, idDireccion, img) VALUES
(1, 2, 1, 'Robo detectado cerca del parque, tengan cuidado.', 1, 'robo_foto.jpg'),
(2, 3, 2, 'Gran hueco en la vía principal, afecta el tráfico.', 3, 'hueco_via.png');

-- Comentarios
INSERT IGNORE INTO comentarios (idComentarios, idUsuario, idPublicacion, fechaComentario, comentario) VALUES
(1, 3, 1, NOW(), 'Gracias por avisar, por allá paso siempre.'),
(2, 1, 2, NOW(), 'Reportado a la secretaría de movilidad.');

-- Likes
INSERT IGNORE INTO likes (idLikes, idUsuario, idPublicacion, fechaLike) VALUES
(1, 1, 1, NOW()),
(2, 3, 1, NOW());
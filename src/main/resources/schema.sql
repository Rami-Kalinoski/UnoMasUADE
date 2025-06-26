-- Eliminar las tablas existentes respetando claves foráneas
DROP TABLE IF EXISTS reseña;
DROP TABLE IF EXISTS participacion;
DROP TABLE IF EXISTS usuario_deporte;
DROP TABLE IF EXISTS partido;
DROP TABLE IF EXISTS usuario;
DROP TABLE IF EXISTS deporte;

-- Crear tabla deporte
CREATE TABLE deporte (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL UNIQUE,
    cantidad_jugadores INT NOT NULL
);

-- Crear tabla usuario
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    contraseña VARCHAR(255) NOT NULL,
    ubicacion VARCHAR(255)
);

-- Crear tabla partido (sin observer)
CREATE TABLE partido (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    organizador_id BIGINT NOT NULL,
    deporte_id BIGINT NOT NULL,
    ubicacion VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    horario TIME NOT NULL,
    estado VARCHAR(50),
    emparejador VARCHAR(50),
    cantidad_jugadores INT NOT NULL,
    duracion_minutos INT NOT NULL,
    FOREIGN KEY (organizador_id) REFERENCES usuario(id),
    FOREIGN KEY (deporte_id) REFERENCES deporte(id)
);

-- Crear tabla usuario_deporte
CREATE TABLE usuario_deporte (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    deporte_id BIGINT NOT NULL,
    nivel VARCHAR(50) NOT NULL,
    favorito BOOLEAN DEFAULT FALSE,
    partidos_ganados INT DEFAULT 0,
    partidos_perdidos INT DEFAULT 0,
    partidos_empatados INT DEFAULT 0,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (deporte_id) REFERENCES deporte(id)
);

-- Crear tabla participacion
CREATE TABLE participacion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    partido_id BIGINT NOT NULL,
    estado VARCHAR(50) NOT NULL,
    resultado VARCHAR(50) NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (partido_id) REFERENCES partido(id)
);

-- Crear tabla reseña
CREATE TABLE reseña (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    usuario_id BIGINT NOT NULL,
    partido_id BIGINT NOT NULL,
    comentario TEXT,
    calificacion INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (partido_id) REFERENCES partido(id)
);

-- Inserciones base de deportes
INSERT INTO deporte (nombre, cantidad_jugadores) VALUES
    ('Fútbol', 22),
    ('Padel', 4),
    ('Tenis', 2),
    ('Básquet', 10),
    ('Rugby', 30),
    ('Vóley', 10);


--CREATE TABLE IF NOT EXISTS usuario (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    username VARCHAR(100),--
--    email VARCHAR(100),--
--    contraseña VARCHAR(100),--
--    ubicacion VARCHAR(100)--
--);--
----
--CREATE TABLE IF NOT EXISTS deporte (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    nombre VARCHAR(100) UNIQUE,--
--    cantidad_jugadores INT--
--);--
----
--CREATE TABLE IF NOT EXISTS usuario_deporte (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    usuario_id BIGINT,--
--    deporte_id BIGINT,--
--    nivel VARCHAR(50) DEFAULT 'PRINCIPIANTE',--
--    favorito BOOLEAN DEFAULT FALSE,--
--    partidos_ganados INT DEFAULT 0,--
--    partidos_perdidos INT DEFAULT 0,--
--    partidos_empatados INT DEFAULT 0,--
--    FOREIGN KEY (usuario_id) REFERENCES usuario(id),--
--    FOREIGN KEY (deporte_id) REFERENCES deporte(id)--
--);--
----
--CREATE TABLE IF NOT EXISTS partido (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    organizador_id BIGINT,--
--    deporte_id BIGINT,--
--    ubicacion VARCHAR(100),--
--    fecha DATE,--
--    horario TIME,--
--    cantidad_jugadores INT,--
--    duracion_minutos INT,--
--    FOREIGN KEY (organizador_id) REFERENCES usuario(id),--
--    FOREIGN KEY (deporte_id) REFERENCES deporte(id)--
--);--
----
--CREATE TABLE IF NOT EXISTS participacion (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    usuario_id BIGINT,--
--    partido_id BIGINT,--
--    estado VARCHAR(50) DEFAULT 'PENDIENTE',--
--    resultado VARCHAR(50) DEFAULT 'INDEFINIDO',--
--    FOREIGN KEY (usuario_id) REFERENCES usuario(id),--
--    FOREIGN KEY (partido_id) REFERENCES partido(id)--
--);--
----
--CREATE TABLE IF NOT EXISTS reseña (--
--    id BIGINT AUTO_INCREMENT PRIMARY KEY,--
--    partido_id BIGINT,--
--    comentario TEXT,--
--    calificacion INT,--
--    FOREIGN KEY (partido_id) REFERENCES partido(id)--
--);--
----
---- Insertar deportes por defecto solo si no existen--
--INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Fútbol', 22);--
--INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Básquet', 10);--
--INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Tenis', 2);--
--INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Rugby', 30);--
--INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Pádel', 4);--
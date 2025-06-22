CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    email VARCHAR(100),
    contraseña VARCHAR(100),
    ubicacion VARCHAR(100)
);

CREATE TABLE deporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) UNIQUE,
    cantidad_jugadores INT
);

CREATE TABLE usuario_deporte (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    deporte_id BIGINT,
    nivel VARCHAR(50) DEFAULT 'PRINCIPIANTE',
    favorito BOOLEAN DEFAULT FALSE,
    partidos_ganados INT DEFAULT 0,
    partidos_perdidos INT DEFAULT 0,
    partidos_empatados INT DEFAULT 0,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (deporte_id) REFERENCES deporte(id)
);

CREATE TABLE partido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    organizador_id BIGINT,
    deporte_id BIGINT,
    ubicacion VARCHAR(100),
    fecha DATE,
    horario TIME,
    cantidad_jugadores INT,
    duracion_minutos INT,
    FOREIGN KEY (organizador_id) REFERENCES usuario(id),
    FOREIGN KEY (deporte_id) REFERENCES deporte(id)
);

CREATE TABLE participacion (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id BIGINT,
    partido_id BIGINT,
    estado VARCHAR(50) DEFAULT 'PENDIENTE',
    resultado VARCHAR(50) DEFAULT 'INDEFINIDO',
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (partido_id) REFERENCES partido(id)
);

CREATE TABLE reseña (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    partido_id BIGINT,
    comentario TEXT,
    calificacion INT,
    FOREIGN KEY (partido_id) REFERENCES partido(id)
);

-- Insertar deportes por defecto solo si no existen
INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Fútbol', 22);
INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Básquet', 10);
INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Tenis', 2);
INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Rugby', 30);
INSERT IGNORE INTO deporte (nombre, cantidad_jugadores) VALUES ('Pádel', 4);
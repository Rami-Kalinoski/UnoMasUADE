package com.adoouade.unomas.dao;

import com.adoouade.unomas.classes.*;
import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PartidoDao {
    // attributes
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private DeporteDao deporteDao;
    @Autowired
    private ParticipacionDao participacionDao;
    @Autowired
    private ReseñaDao reseñaDao;


    // pass
    public Partido toModel(Long id) {
        return obtenerPartido(id);
    }
    public Partido toModel(PartidoDto dto) {
        Partido partido = new Partido();
        partido.setId(dto.getId());
        partido.setOrganizador(usuarioDao.obtenerUsuario(dto.getOrganizadorId()));
        partido.setDeporte(deporteDao.obtenerDeporte(dto.getDeporteId()));
        partido.setUbicacion(dto.getUbicacion());
        partido.setFecha(dto.getFecha());
        partido.setHorario(dto.getHorario());

        String estado = dto.getEstado();
        IEstadoPartido estadoPartido = null;
        if (estado=="NECESITA_JUGADORES") {
            estadoPartido = new NecesitaJugadores();
        } else if (estado=="PARTIDO_ARMADO") {
            estadoPartido = new PartidoArmado();
        } else if (estado=="CONFIRMADO") {
            estadoPartido = new Confirmado();
        } else if (estado=="EN_JUEGO") {
            estadoPartido = new EnJuego();
        } else if (estado=="FINALIZADO") {
            estadoPartido = new Finalizado();
        } else {
            estadoPartido = new Cancelado();
        }
        partido.setEstado(estadoPartido);
        List<Participacion> participaciones = participacionDao.obtenerParticipaciones().stream()
                .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(dto.getId()))
                .collect(Collectors.toList());
        partido.setParticipaciones(participaciones);
        List<Reseña> reseñas = reseñaDao.obtenerReseñas().stream()
                .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(dto.getId()))
                .collect(Collectors.toList());
        partido.setReseñas(reseñas);

        String emparejador = dto.getEmparejador();
        Emparejador e = new Emparejador();
        if (emparejador=="PorCercania") {
            e.CambiarEstrategia(new PorCercania());
        } else if (emparejador=="PorNivel") {
            e.CambiarEstrategia(new PorNivel());
        } else {
            e.CambiarEstrategia(new PorHistorial());
        }

        partido.setObserver(new Notificador());
        partido.setCantidadJugadores(dto.getCantidadJugadores());
        partido.setDuracionMinutos(dto.getDuracionMinutos());
        return partido;
    }
    public PartidoDto toDto(Partido partido) {
        return new PartidoDto(partido.getId(), partido.getOrganizador().getId(), partido.getDeporte().getId(), partido.getUbicacion(), partido.getFecha(), partido.getHorario(), partido.getEstado().toString(), partido.getEmparejador().getEstrategia().getClass().getName(), partido.getCantidadJugadores(), partido.getDuracionMinutos());
    }

    //methods
    // Partido ---------------------------------------------------------------------------------------------------------
    public Partido crearPartido(PartidoDto dto) {
        String sql = "INSERT INTO partido (organizador_id, deporte_id, ubicacion, fecha, horario, estado, emparejador, cantidad_jugadores, duracion_minutos) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getOrganizadorId());
            stmt.setLong(2, dto.getDeporteId());
            stmt.setString(3, dto.getUbicacion());
            stmt.setDate(4, java.sql.Date.valueOf(dto.getFecha()));
            stmt.setTime(5, java.sql.Time.valueOf(dto.getHorario()));
            stmt.setString(6, dto.getEstado());
            stmt.setString(7, dto.getEmparejador());
            stmt.setInt(8, dto.getCantidadJugadores());
            stmt.setInt(9, dto.getDuracionMinutos());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Partido partido = new Partido();
                    Long partidoId = rs.getLong(1);
                    partido.setId(partidoId);

                    Usuario organizador = new Usuario();
                    organizador.setId(dto.getOrganizadorId());
                    partido.setOrganizador(organizador);

                    Deporte deporte = new Deporte();
                    deporte.setId(dto.getDeporteId());
                    partido.setDeporte(deporte);

                    partido.setUbicacion(dto.getUbicacion());
                    partido.setFecha(dto.getFecha());
                    partido.setHorario(dto.getHorario());

                    String estadoStr = dto.getEstado();
                    String emparejadorStr = dto.getEmparejador();

                    IEstadoPartido estado = null;
                    if (estadoStr=="NECESITA_JUGADORES") {
                        estado = new NecesitaJugadores();
                    } else if (estadoStr=="PARTIDO_ARMADO") {
                        estado = new PartidoArmado();
                    } else if (estadoStr=="CONFIRMADO") {
                        estado = new Confirmado();
                    } else if (estadoStr=="EN_JUEGO") {
                        estado = new EnJuego();
                    } else if (estadoStr=="FINALIZADO") {
                        estado = new Finalizado();
                    } else {
                        estado = new Cancelado();
                    }
                    partido.setEstado(estado);
                    List<Participacion> participaciones = participacionDao.obtenerParticipaciones().stream()
                            .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                            .collect(Collectors.toList());
                    partido.setParticipaciones(participaciones);
                    List<Reseña> reseñas = reseñaDao.obtenerReseñas().stream()
                            .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                            .collect(Collectors.toList());
                    partido.setReseñas(reseñas);

                    Emparejador emparejador = new Emparejador();
                    if (emparejadorStr=="PorCercania") {
                        emparejador.CambiarEstrategia(new PorCercania());
                    } else if (emparejadorStr=="PorNivel") {
                        emparejador.CambiarEstrategia(new PorNivel());
                    } else {
                        emparejador.CambiarEstrategia(new PorHistorial());
                    }
                    partido.setEmparejador(emparejador);


                    partido.setCantidadJugadores(dto.getCantidadJugadores());
                    partido.setDuracionMinutos(dto.getDuracionMinutos());

                    return partido;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Partido obtenerPartido(Long id) {
        String sql = "SELECT * FROM partido WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Partido partido = new Partido();
                Long partidoId = rs.getLong("id");
                partido.setId(partidoId);
                // Importante: solo cargamos los IDs de relaciones, no los objetos completos todavía
                Usuario organizador = new Usuario();
                organizador.setId(rs.getLong("organizador_id"));
                partido.setOrganizador(organizador);

                Deporte deporte = new Deporte();
                deporte.setId(rs.getLong("deporte_id"));
                partido.setDeporte(deporte);

                partido.setUbicacion(rs.getString("ubicacion"));
                partido.setFecha(rs.getDate("fecha").toLocalDate());
                partido.setHorario(rs.getTime("horario").toLocalTime());

                String estadoStr = rs.getString("estado");
                String emparejadorStr = rs.getString("emparejador");

                IEstadoPartido estado = null;
                if (estadoStr=="NECESITA_JUGADORES") {
                    estado = new NecesitaJugadores();
                } else if (estadoStr=="PARTIDO_ARMADO") {
                    estado = new PartidoArmado();
                } else if (estadoStr=="CONFIRMADO") {
                    estado = new Confirmado();
                } else if (estadoStr=="EN_JUEGO") {
                    estado = new EnJuego();
                } else if (estadoStr=="FINALIZADO") {
                    estado = new Finalizado();
                } else {
                    estado = new Cancelado();
                }
                partido.setEstado(estado);
                List<Participacion> participaciones = participacionDao.obtenerParticipaciones().stream()
                        .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                        .collect(Collectors.toList());
                partido.setParticipaciones(participaciones);
                List<Reseña> reseñas = reseñaDao.obtenerReseñas().stream()
                        .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                        .collect(Collectors.toList());
                partido.setReseñas(reseñas);

                Emparejador emparejador = new Emparejador();
                if (emparejadorStr=="PorCercania") {
                    emparejador.CambiarEstrategia(new PorCercania());
                } else if (emparejadorStr=="PorNivel") {
                    emparejador.CambiarEstrategia(new PorNivel());
                } else {
                    emparejador.CambiarEstrategia(new PorHistorial());
                }
                partido.setEmparejador(emparejador);

                partido.setCantidadJugadores(rs.getInt("cantidad_jugadores"));
                partido.setDuracionMinutos(rs.getInt("duracion_minutos"));

                return partido;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Partido> obtenerPartidos() {
        List<Partido> partidos = new ArrayList<>();
        String sql = "SELECT * FROM partido";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Partido partido = new Partido();
                Long partidoId = rs.getLong("id");
                partido.setId(partidoId);

                Usuario organizador = new Usuario();
                organizador.setId(rs.getLong("organizador_id"));
                partido.setOrganizador(organizador);

                Deporte deporte = new Deporte();
                deporte.setId(rs.getLong("deporte_id"));
                partido.setDeporte(deporte);

                partido.setUbicacion(rs.getString("ubicacion"));
                partido.setFecha(rs.getDate("fecha").toLocalDate());
                partido.setHorario(rs.getTime("horario").toLocalTime());

                String estadoStr = rs.getString("estado");
                String emparejadorStr = rs.getString("emparejador");

                IEstadoPartido estado = null;
                if (estadoStr=="NECESITA_JUGADORES") {
                    estado = new NecesitaJugadores();
                } else if (estadoStr=="PARTIDO_ARMADO") {
                    estado = new PartidoArmado();
                } else if (estadoStr=="CONFIRMADO") {
                    estado = new Confirmado();
                } else if (estadoStr=="EN_JUEGO") {
                    estado = new EnJuego();
                } else if (estadoStr=="FINALIZADO") {
                    estado = new Finalizado();
                } else {
                    estado = new Cancelado();
                }
                partido.setEstado(estado);
                List<Participacion> participaciones = participacionDao.obtenerParticipaciones().stream()
                        .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                        .collect(Collectors.toList());
                partido.setParticipaciones(participaciones);
                List<Reseña> reseñas = reseñaDao.obtenerReseñas().stream()
                        .filter(p -> p.getPartido() != null && p.getPartido().getId().equals(partidoId))
                        .collect(Collectors.toList());
                partido.setReseñas(reseñas);

                Emparejador emparejador = new Emparejador();
                if (emparejadorStr=="PorCercania") {
                    emparejador.CambiarEstrategia(new PorCercania());
                } else if (emparejadorStr=="PorNivel") {
                    emparejador.CambiarEstrategia(new PorNivel());
                } else {
                    emparejador.CambiarEstrategia(new PorHistorial());
                }
                partido.setEmparejador(emparejador);

                partido.setCantidadJugadores(rs.getInt("cantidad_jugadores"));
                partido.setDuracionMinutos(rs.getInt("duracion_minutos"));

                partidos.add(partido);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return partidos;
    }
    public boolean modificarPartido(PartidoDto dto) {
        String sql = "UPDATE partido SET " +
                "organizador_id = ?, deporte_id = ?, ubicacion = ?, fecha = ?, horario = ?, " +
                "estado = ?, emparejador = ?, cantidad_jugadores = ?, duracion_minutos = ? " +
                "WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, dto.getOrganizadorId());
            stmt.setLong(2, dto.getDeporteId());
            stmt.setString(3, dto.getUbicacion());
            stmt.setDate(4, java.sql.Date.valueOf(dto.getFecha()));
            stmt.setTime(5, java.sql.Time.valueOf(dto.getHorario()));
            stmt.setString(6, dto.getEstado());
            stmt.setString(7, dto.getEmparejador());
            stmt.setInt(8, dto.getCantidadJugadores());
            stmt.setInt(9, dto.getDuracionMinutos());
            stmt.setLong(10, dto.getId()); // el id a modificar

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarPartido(Long id) {
        String sql = "DELETE FROM partido WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Reseña ----------------------------------------------------------------------------------------------------------

}
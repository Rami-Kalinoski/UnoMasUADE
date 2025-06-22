package com.adoouade.unomas.dao;

import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.Deporte;
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

@Repository
public class PartidoDao {
    // attributes
    @Autowired
    private DataSource dataSource;

    //methods
    // Partido ---------------------------------------------------------------------------------------------------------
    public Partido crearPartido(PartidoDto dto) {
        String sql = "INSERT INTO partido (organizador_id, deporte_id, ubicacion, fecha, horario, cantidad_jugadores, duracion_minutos) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getOrganizadorId());
            stmt.setLong(2, dto.getDeporteId());
            stmt.setString(3, dto.getUbicacion());
            stmt.setDate(4, java.sql.Date.valueOf(dto.getFecha()));
            stmt.setTime(5, java.sql.Time.valueOf(dto.getHorario()));
            stmt.setInt(6, dto.getCantidadJugadores());
            stmt.setInt(7, dto.getDuracionMinutos());

            int filas = stmt.executeUpdate();

            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Partido partido = new Partido();
                    partido.setId(rs.getLong(1));

                    Usuario organizador = new Usuario();
                    organizador.setId(dto.getOrganizadorId());
                    partido.setOrganizador(organizador);

                    Deporte deporte = new Deporte();
                    deporte.setId(dto.getDeporteId());
                    partido.setDeporte(deporte);

                    partido.setUbicacion(dto.getUbicacion());
                    partido.setFecha(dto.getFecha());
                    partido.setHorario(dto.getHorario());
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
                partido.setId(rs.getLong("id"));
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
                partido.setId(rs.getLong("id"));

                Usuario organizador = new Usuario();
                organizador.setId(rs.getLong("organizador_id"));
                partido.setOrganizador(organizador);

                Deporte deporte = new Deporte();
                deporte.setId(rs.getLong("deporte_id"));
                partido.setDeporte(deporte);

                partido.setUbicacion(rs.getString("ubicacion"));
                partido.setFecha(rs.getDate("fecha").toLocalDate());
                partido.setHorario(rs.getTime("horario").toLocalTime());
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
                "cantidad_jugadores = ?, duracion_minutos = ? " +
                "WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, dto.getOrganizadorId());
            stmt.setLong(2, dto.getDeporteId());
            stmt.setString(3, dto.getUbicacion());
            stmt.setDate(4, java.sql.Date.valueOf(dto.getFecha()));
            stmt.setTime(5, java.sql.Time.valueOf(dto.getHorario()));
            stmt.setInt(6, dto.getCantidadJugadores());
            stmt.setInt(7, dto.getDuracionMinutos());
            stmt.setLong(8, dto.getId()); // el id a modificar

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
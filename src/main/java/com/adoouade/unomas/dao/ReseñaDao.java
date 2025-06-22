package com.adoouade.unomas.dao;

import com.adoouade.unomas.model.Reseña;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.dto.ReseñaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReseñaDao {
    // attributes
    @Autowired
    private DataSource dataSource;

    // methods
    public Reseña crearReseña(ReseñaDto dto) {
        String sql = "INSERT INTO reseña (partido_id, comentario, calificacion) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getPartidoId());
            stmt.setString(2, dto.getComentario());
            stmt.setInt(3, dto.getCalificacion());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Partido partido = new Partido();
                    partido.setId(dto.getPartidoId());
                    return new Reseña(
                            rs.getLong(1),
                            partido,
                            dto.getComentario(),
                            dto.getCalificacion()
                    );
                }
                rs.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Reseña obtenerReseña(Long id) {
        String sql = "SELECT * FROM reseña WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Partido partido = new Partido();
                partido.setId(rs.getLong("partido_id"));

                return new Reseña(
                        rs.getLong("id"),
                        partido,
                        rs.getString("comentario"),
                        rs.getInt("calificacion")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Reseña> obtenerReseñas() {
        List<Reseña> reseñas = new ArrayList<>();
        String sql = "SELECT * FROM reseña";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Partido partido = new Partido();
                partido.setId(rs.getLong("partido_id"));

                Reseña reseña = new Reseña(
                        rs.getLong("id"),
                        partido,
                        rs.getString("comentario"),
                        rs.getInt("calificacion")
                );

                reseñas.add(reseña);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reseñas;
    }
    public boolean modificarReseña(ReseñaDto dto) {
        String sql = "UPDATE reseña SET partido_id = ?, comentario = ?, calificacion = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, dto.getPartidoId());
            stmt.setString(2, dto.getComentario());
            stmt.setInt(3, dto.getCalificacion());
            stmt.setLong(4, dto.getId());

            int filas = stmt.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarReseña(Long id) {
        String sql = "DELETE FROM reseña WHERE id = ?";

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

}
package com.adoouade.unomas.dao;

import com.adoouade.unomas.model.Reseña;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.dto.ReseñaDto;
import com.adoouade.unomas.model.Usuario;
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

    // pass
    public Reseña toModel(ReseñaDto dto) {
        return new Reseña(dto.getId(), new UsuarioDao().obtenerUsuario(dto.getUsuarioId()), new PartidoDao().obtenerPartido(dto.getPartidoId()), dto.getComentario(), dto.getCalificacion());
    }

    public ReseñaDto toDto(Reseña reseña) {
        return new ReseñaDto(reseña.getId(), reseña.getPartido().getId(), reseña.getComentario(), reseña.getCalificacion());
    }

    // methods
    public Reseña crearReseña(ReseñaDto dto) {
        String sql = "INSERT INTO reseña (usuario_id, partido_id, comentario, calificacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getUsuarioId());
            stmt.setLong(2, dto.getPartidoId());
            stmt.setString(3, dto.getComentario());
            stmt.setInt(4, dto.getCalificacion());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(dto.getUsuarioId());
                    Partido partido = new Partido();
                    partido.setId(dto.getPartidoId());
                    return new Reseña(
                            rs.getLong(1),
                            usuario,
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
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("usuario_id"));
                Partido partido = new Partido();
                partido.setId(rs.getLong("partido_id"));

                return new Reseña(
                        rs.getLong("id"),
                        usuario,
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
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("usuario_id"));
                Partido partido = new Partido();
                partido.setId(rs.getLong("partido_id"));

                Reseña reseña = new Reseña(
                        rs.getLong("id"),
                        usuario,
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
        String sql = "UPDATE reseña SET usuario_id = ?, partido_id = ?, comentario = ?, calificacion = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, dto.getUsuarioId());
            stmt.setLong(2, dto.getPartidoId());
            stmt.setString(3, dto.getComentario());
            stmt.setInt(4, dto.getCalificacion());
            stmt.setLong(5, dto.getId());

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
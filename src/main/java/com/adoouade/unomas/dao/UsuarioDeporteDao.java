package com.adoouade.unomas.dao;

import com.adoouade.unomas.model.UsuarioDeporte;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.enums.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UsuarioDeporteDao {
    // attributes
    @Autowired
    private DataSource dataSource;

    // methods
    public UsuarioDeporte crearUsuarioDeporte(UsuarioDeporteDto dto) {
        String sql = "INSERT INTO usuario_deporte (usuario_id, deporte_id, nivel, favorito, partidos_ganados, partidos_perdidos, partidos_empatados) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getUsuarioId());
            stmt.setLong(2, dto.getDeporteId());
            stmt.setString(3, dto.getNivel().toString());
            stmt.setBoolean(4, dto.isFavorito());
            stmt.setInt(5, dto.getPartidosGanados());
            stmt.setInt(6, dto.getPartidosPerdidos());
            stmt.setInt(7, dto.getPartidosEmpatados());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Usuario usuario = new Usuario(); usuario.setId(dto.getUsuarioId());
                    Deporte deporte = new Deporte(); deporte.setId(dto.getDeporteId());
                    return new UsuarioDeporte(
                            rs.getLong(1),
                            usuario,
                            deporte,
                            dto.getNivel(),
                            dto.isFavorito(),
                            dto.getPartidosGanados(),
                            dto.getPartidosPerdidos(),
                            dto.getPartidosEmpatados()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public UsuarioDeporte obtenerUsuarioDeporte(Long usuarioId, Long deporteId) {
        String sql = "SELECT * FROM usuario_deporte WHERE usuario_id = ? AND deporte_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            stmt.setLong(2, deporteId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(); usuario.setId(usuarioId);
                Deporte deporte = new Deporte(); deporte.setId(deporteId);
                return new UsuarioDeporte(
                        rs.getLong("id"),
                        usuario,
                        deporte,
                        Nivel.valueOf(rs.getString("nivel")),
                        rs.getBoolean("favorito"),
                        rs.getInt("partidos_ganados"),
                        rs.getInt("partidos_perdidos"),
                        rs.getInt("partidos_empatados")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<UsuarioDeporte> obtenerUsuarioDeportes() {
        List<UsuarioDeporte> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario_deporte";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(); usuario.setId(rs.getLong("usuario_id"));
                Deporte deporte = new Deporte(); deporte.setId(rs.getLong("deporte_id"));

                lista.add(new UsuarioDeporte(
                        rs.getLong("id"),
                        usuario,
                        deporte,
                        Nivel.valueOf(rs.getString("nivel")),
                        rs.getBoolean("favorito"),
                        rs.getInt("partidos_ganados"),
                        rs.getInt("partidos_perdidos"),
                        rs.getInt("partidos_empatados")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public boolean modificarUsuarioDeporte(UsuarioDeporteDto dto) {
        String sql = "UPDATE usuario_deporte SET nivel = ?, favorito = ?, partidos_ganados = ?, partidos_perdidos = ?, partidos_empatados = ? " +
                "WHERE usuario_id = ? AND deporte_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getNivel().toString());
            stmt.setBoolean(2, dto.isFavorito());
            stmt.setInt(3, dto.getPartidosGanados());
            stmt.setInt(4, dto.getPartidosPerdidos());
            stmt.setInt(5, dto.getPartidosEmpatados());
            stmt.setLong(6, dto.getUsuarioId());
            stmt.setLong(7, dto.getDeporteId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarUsuarioDeporte(Long usuarioId, Long deporteId) {
        String sql = "DELETE FROM usuario_deporte WHERE usuario_id = ? AND deporte_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            stmt.setLong(2, deporteId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
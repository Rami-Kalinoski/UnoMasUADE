package com.adoouade.unomas.dao;

import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.UsuarioDeporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DeporteDao {
    // attributes
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UsuarioDeporteDao usuarioDeporteDao;

    // methods
    public Deporte obtenerDeporte(Long id) {
        String sql = "SELECT * FROM deporte WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                List<UsuarioDeporte> relacionados = usuarioDeporteDao.obtenerUsuarioDeportes().stream()
                        .filter(ud -> ud.getDeporte() != null && ud.getDeporte().getId().equals(id))
                        .collect(Collectors.toList());

                return new Deporte(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad_jugadores"),
                        relacionados
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Deporte> obtenerDeportes() {
        List<Deporte> deportes = new ArrayList<>();
        String sql = "SELECT * FROM deporte";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            List<UsuarioDeporte> todos = usuarioDeporteDao.obtenerUsuarioDeportes();

            while (rs.next()) {
                Long id = rs.getLong("id");

                List<UsuarioDeporte> relacionados = todos.stream()
                        .filter(ud -> ud.getDeporte() != null && ud.getDeporte().getId().equals(id))
                        .collect(Collectors.toList());

                deportes.add(new Deporte(
                        id,
                        rs.getString("nombre"),
                        rs.getInt("cantidad_jugadores"),
                        relacionados
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deportes;
    }
}
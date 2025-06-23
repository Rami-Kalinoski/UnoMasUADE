package com.adoouade.unomas.dao;

import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
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
import java.util.stream.Collectors;


@Repository
public class UsuarioDao {
    // attributes
    @Autowired
    private DataSource dataSource;

    // pass
    public Usuario toModel(Long id) {
        Usuario usuario = obtenerUsuario(id);
        List<UsuarioDeporte> usuarioDeportes = new UsuarioDeporteDao().obtenerUsuarioDeportes().stream()
                .filter(ud -> ud.getUsuario() != null && ud.getUsuario().getId().equals(id))
                .collect(Collectors.toList());;
        usuario.setUsuarioDeportes(usuarioDeportes);
        usuario.setUsuarioDao(new UsuarioDao());
        usuario.setUsuarioDeporteDao(new UsuarioDeporteDao());
        usuario.setParticipacionDao(new ParticipacionDao());
        return usuario;
    }

    public Usuario toModel(UsuarioDto dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setUsername(dto.getUsername());
        usuario.setContraseña(dto.getContraseña());
        usuario.setUbicacion(dto.getUbicacion());
        List<UsuarioDeporte> usuarioDeportes = new UsuarioDeporteDao().obtenerUsuarioDeportes().stream()
                .filter(ud -> ud.getUsuario() != null && ud.getUsuario().getId().equals(dto.getId()))
                .collect(Collectors.toList());
        usuario.setUsuarioDeportes(usuarioDeportes);
        return usuario;
    }

    public UsuarioDto toDto(Usuario usuario) {
        return new UsuarioDto(usuario.getId(), usuario.getUsername(), usuario.getEmail(), usuario.getContraseña(), usuario.getUbicacion());
    }

    // methods
    public Usuario crearUsuario(UsuarioDto dto) {
        String sql = "INSERT INTO usuario (username, email, contraseña, ubicacion) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getEmail());
            stmt.setString(3, dto.getContraseña());
            stmt.setString(4, dto.getUbicacion());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return new Usuario(
                            rs.getLong(1),
                            dto.getUsername(),
                            dto.getEmail(),
                            dto.getContraseña(),
                            dto.getUbicacion(),
                            null,
                            null,
                            null,
                            null
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Usuario obtenerUsuario(Long id) {
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("ubicacion"),
                        null,
                        null,
                        null,
                        null
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("contraseña"),
                        rs.getString("ubicacion"),
                        null,
                        null,
                        null,
                        null
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }
    public boolean login(String username, String email, String password) {
        String sql = "SELECT * FROM usuario WHERE username = ? AND email = ? AND contraseña = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean modificarUsuario(UsuarioDto dto) {
        String sql = "UPDATE usuario SET username = ?, email = ?, contraseña = ?, ubicacion = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getUsername());
            stmt.setString(2, dto.getEmail());
            stmt.setString(3, dto.getContraseña());
            stmt.setString(4, dto.getUbicacion());
            stmt.setLong(5, dto.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarUsuario(Long id) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
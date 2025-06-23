package com.adoouade.unomas.dao;

import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ParticipacionDao {
    // attributes
    @Autowired
    private DataSource dataSource;

    // pass
    public Participacion toModel(ParticipacionDto dto) {
        Participacion participacion = new Participacion();
        participacion.setId(dto.getId());
        participacion.setUsuario(new UsuarioDao().toModel(dto.getUsuarioId()));
        participacion.setPartido(new PartidoDao().toModel(dto.getPartidoId()));
        participacion.setEstado(dto.getEstado());
        participacion.setResultado(dto.getResultado());
        return participacion;
    }

    public ParticipacionDto toDto(Participacion participacion) {
        return new ParticipacionDto(participacion.getId(), participacion.getUsuario().getId(), participacion.getPartido().getId(), participacion.getEstado(), participacion.getResultado());
    }

    // methods
    public Participacion crearParticipacion(ParticipacionDto dto) {
        String sql = "INSERT INTO participacion (usuario_id, partido_id, estado, resultado) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, dto.getUsuarioId());
            stmt.setLong(2, dto.getPartidoId());
            stmt.setString(3, dto.getEstado().toString());
            stmt.setString(4, dto.getResultado().toString());

            int filas = stmt.executeUpdate();
            if (filas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    Usuario usuario = new Usuario(); usuario.setId(dto.getUsuarioId());
                    Partido partido = new Partido(); partido.setId(dto.getPartidoId());
                    return new Participacion(
                            rs.getLong(1),
                            usuario,
                            partido,
                            dto.getEstado(),
                            dto.getResultado()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public Participacion obtenerParticipacion(Long usuarioId, Long partidoId) {
        String sql = "SELECT * FROM participacion WHERE usuario_id = ? AND partido_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            stmt.setLong(2, partidoId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario usuario = new Usuario(); usuario.setId(usuarioId);
                Partido partido = new Partido(); partido.setId(partidoId);
                return new Participacion(
                        rs.getLong("id"),
                        usuario,
                        partido,
                        EstadoParticipacion.valueOf(rs.getString("estado")),
                        Resultado.valueOf(rs.getString("resultado"))
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public List<Participacion> obtenerParticipaciones() {
        List<Participacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM participacion";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(); usuario.setId(rs.getLong("usuario_id"));
                Partido partido = new Partido(); partido.setId(rs.getLong("partido_id"));

                lista.add(new Participacion(
                        rs.getLong("id"),
                        usuario,
                        partido,
                        EstadoParticipacion.valueOf(rs.getString("estado")),
                        Resultado.valueOf(rs.getString("resultado"))
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
    public boolean modificarParticipacion(ParticipacionDto dto) {
        String sql = "UPDATE participacion SET estado = ?, resultado = ? WHERE usuario_id = ? AND partido_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, dto.getEstado().toString());
            stmt.setString(2, dto.getResultado().toString());
            stmt.setLong(3, dto.getUsuarioId());
            stmt.setLong(4, dto.getPartidoId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean eliminarParticipacion(Long usuarioId, Long partidoId) {
        String sql = "DELETE FROM participacion WHERE usuario_id = ? AND partido_id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, usuarioId);
            stmt.setLong(2, partidoId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
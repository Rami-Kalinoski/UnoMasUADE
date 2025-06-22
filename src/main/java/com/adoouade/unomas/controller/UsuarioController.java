package com.adoouade.unomas.controller;

import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class UsuarioController {
    // attributes
    @Autowired
    private static Usuario usuario;
    private String tokenLogueado;

    // methods
    // Usuario ---------------------------------------------------------------------------------------------------------
    public Usuario CrearUsuario(UsuarioDto usuarioDto) {
        return usuario.getUsuarioDao().crearUsuario(usuarioDto);
    }
    public Usuario ObtenerUsuario(Long id) {
        return usuario.getUsuarioDao().obtenerUsuario(id);
    }
    public List<Usuario> ObtenerUsuarios() {
        return usuario.getUsuarioDao().obtenerUsuarios();
    }
    public boolean Login(String username, String email, String password) {
        return usuario.getUsuarioDao().login(username, email, password);
    }
    public boolean ModificarUsuario(UsuarioDto usuarioDto) {
        return usuario.getUsuarioDao().modificarUsuario(usuarioDto);
    }
    public boolean EliminarUsuario(Long id) {
        return usuario.getUsuarioDao().eliminarUsuario(id);
    }

    // UsuarioDeporte --------------------------------------------------------------------------------------------------
    public UsuarioDeporte CrearUsuarioDeporte(UsuarioDeporteDto usuarioDeporteDto) {
        return usuario.getUsuarioDeporteDao().crearUsuarioDeporte(usuarioDeporteDto);
    }
    public UsuarioDeporte ObtenerUsuarioDeporte(Long idUsuario, Long idDeporte) {
        return usuario.getUsuarioDeporteDao().obtenerUsuarioDeporte(idUsuario, idDeporte);
    }
    public List<UsuarioDeporte> ObtenerUsuarioDeportes() {
        return usuario.getUsuarioDeporteDao().obtenerUsuarioDeportes();
    }
    public boolean ModificarUsuarioDeporte(UsuarioDeporteDto usuarioDeporteDto) {
        return usuario.getUsuarioDeporteDao().modificarUsuarioDeporte(usuarioDeporteDto);
    }
    public boolean EliminarUsuarioDeporte(Long idUsuario, Long idDeporte) {
        return usuario.getUsuarioDeporteDao().eliminarUsuarioDeporte(idUsuario, idDeporte);
    }

    // Participacion ---------------------------------------------------------------------------------------------------
    public Participacion CrearParticipacion(ParticipacionDto participacionDto) {
        return usuario.getParticipacionDao().crearParticipacion(participacionDto);
    }
    public Participacion ObtenerParticipacion(Long idUsuario, Long idPartido) {
        return usuario.getParticipacionDao().obtenerParticipacion(idUsuario, idPartido);
    }
    public List<Participacion> ObtenerParticipaciones() {
        return usuario.getParticipacionDao().obtenerParticipaciones();
    }
    public boolean ModificarParticipacion(ParticipacionDto participacionDto) {
        return usuario.getParticipacionDao().modificarParticipacion(participacionDto);
    }
    public boolean EliminarParticipacion(Long idUsuario, Long idPartido) {
        return usuario.getParticipacionDao().eliminarParticipacion(idUsuario, idPartido);
    }
}
package com.adoouade.unomas.controller;

import com.adoouade.unomas.dao.ParticipacionDao;
import com.adoouade.unomas.dao.UsuarioDao;
import com.adoouade.unomas.dao.UsuarioDeporteDao;
import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Data
@Controller
public class UsuarioController {
    // attributes
    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private ParticipacionDao participacionDao;
    @Autowired
    private UsuarioDeporteDao usuarioDeporteDao;
    private Usuario usuario;
    private String tokenLogueado;

    // methods
    // Usuario ---------------------------------------------------------------------------------------------------------
    public Usuario CrearUsuario(UsuarioDto usuarioDto) {
        return usuarioDao.crearUsuario(usuarioDto);
    }
    public Usuario ObtenerUsuario(Long id) {
        return usuarioDao.obtenerUsuario(id);
    }
    public List<Usuario> ObtenerUsuarios() {
        return usuarioDao.obtenerUsuarios();
    }
    public boolean Login(String username, String email, String password) {
        return usuarioDao.login(username, email, password);
    }
    public boolean ModificarUsuario(UsuarioDto usuarioDto) {
        return usuarioDao.modificarUsuario(usuarioDto);
    }
    public boolean EliminarUsuario(Long id) {
        return usuarioDao.eliminarUsuario(id);
    }

    // UsuarioDeporte --------------------------------------------------------------------------------------------------
    public UsuarioDeporte CrearUsuarioDeporte(UsuarioDeporteDto usuarioDeporteDto) {
        return usuarioDeporteDao.crearUsuarioDeporte(usuarioDeporteDto);
    }
    public UsuarioDeporte ObtenerUsuarioDeporte(Long idUsuario, Long idDeporte) {
        return usuarioDeporteDao.obtenerUsuarioDeporte(idUsuario, idDeporte);
    }
    public List<UsuarioDeporte> ObtenerUsuarioDeportes() {
        return usuarioDeporteDao.obtenerUsuarioDeportes();
    }
    public boolean ModificarUsuarioDeporte(UsuarioDeporteDto usuarioDeporteDto) {
        return usuarioDeporteDao.modificarUsuarioDeporte(usuarioDeporteDto);
    }
    public boolean EliminarUsuarioDeporte(Long idUsuario, Long idDeporte) {
        return usuarioDeporteDao.eliminarUsuarioDeporte(idUsuario, idDeporte);
    }

    // Participacion ---------------------------------------------------------------------------------------------------
    public Participacion CrearParticipacion(ParticipacionDto participacionDto) {
        return participacionDao.crearParticipacion(participacionDto);
    }
    public Participacion ObtenerParticipacion(Long idUsuario, Long idPartido) {
        return participacionDao.obtenerParticipacion(idUsuario, idPartido);
    }
    public List<Participacion> ObtenerParticipaciones() {
        return participacionDao.obtenerParticipaciones();
    }
    public boolean ModificarParticipacion(ParticipacionDto participacionDto) {
        return participacionDao.modificarParticipacion(participacionDto);
    }
    public boolean EliminarParticipacion(Long idUsuario, Long idPartido) {
        return participacionDao.eliminarParticipacion(idUsuario, idPartido);
    }
}
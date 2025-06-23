package com.adoouade.unomas.controller;

import com.adoouade.unomas.dao.PartidoDao;
import com.adoouade.unomas.dao.ReseñaDao;
import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.dto.ReseñaDto;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Reseña;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Data
@Controller
public class PartidoController {
    // attributes
    @Autowired
    private PartidoDao partidoDao;
    @Autowired
    private ReseñaDao reseñaDao;
    private Partido partidoSeleccionado;

    // methods
    // Partido ---------------------------------------------------------------------------------------------------------
    public Partido CrearPartido(PartidoDto partidoDto) { return partidoDao.crearPartido(partidoDto); }
    public Partido ObtenerPartido(Long id) { return partidoDao.obtenerPartido(id); }
    public List<Partido> ObtenerPartidos() { return partidoDao.obtenerPartidos(); }
    public boolean ModificarPartido(PartidoDto partidoDto) { return partidoDao.modificarPartido(partidoDto); }
    public boolean EliminarPartido(Long id) { return partidoDao.eliminarPartido(id); }

    // Reseña ----------------------------------------------------------------------------------------------------------
    public Reseña CrearReseña(ReseñaDto reseñaDto) { return reseñaDao.crearReseña(reseñaDto); }
    public Reseña ObtenerReseña(Long id) { return reseñaDao.obtenerReseña(id); }
    public List<Reseña> ObtenerReseñas() { return reseñaDao.obtenerReseñas(); }
    public boolean ModificarReseña(ReseñaDto reseñaDto) { return reseñaDao.modificarReseña(reseñaDto); }
    public boolean EliminarReseña(Long id) { return reseñaDao.eliminarReseña(id); }

}
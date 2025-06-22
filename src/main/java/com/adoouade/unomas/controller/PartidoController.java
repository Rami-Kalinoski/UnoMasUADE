package com.adoouade.unomas.controller;

import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.dto.ReseñaDto;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Reseña;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
public class PartidoController {
    // attributes
    @Autowired
    private Partido partidoSeleccionado;

    // methods
    // Partido ---------------------------------------------------------------------------------------------------------
    public Partido CrearPartido(PartidoDto partidoDto) { return partidoSeleccionado.getPartidoDao().crearPartido(partidoDto); }
    public Partido ObtenerPartido(Long id) { return partidoSeleccionado.getPartidoDao().obtenerPartido(id); }
    public List<Partido> ObtenerPartidos() { return partidoSeleccionado.getPartidoDao().obtenerPartidos(); }
    public boolean ModificarPartido(PartidoDto partidoDto) { return partidoSeleccionado.getPartidoDao().modificarPartido(partidoDto); }
    public boolean EliminarPartido(Long id) { return partidoSeleccionado.getPartidoDao().eliminarPartido(id); }

    // Reseña ----------------------------------------------------------------------------------------------------------
    public Reseña CrearReseña(ReseñaDto reseñaDto) { return partidoSeleccionado.getReseñaDao().crearReseña(reseñaDto); }
    public Reseña ObtenerReseña(Long id) { return partidoSeleccionado.getReseñaDao().obtenerReseña(id); }
    public List<Reseña> ObtenerReseñas() { return partidoSeleccionado.getReseñaDao().obtenerReseñas(); }
    public boolean ModificarReseña(ReseñaDto reseñaDto) { return partidoSeleccionado.getReseñaDao().modificarReseña(reseñaDto); }
    public boolean EliminarReseña(Long id) { return partidoSeleccionado.getReseñaDao().eliminarReseña(id); }

}
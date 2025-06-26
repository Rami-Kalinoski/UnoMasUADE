package com.adoouade.unomas.classes;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.dao.PartidoDao;
import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Confirmado implements IEstadoPartido {
    public void ManejarPartido(Partido partido) {
        if (partido.getFecha().equals(LocalDate.now()) && partido.getHorario().isBefore(LocalTime.now())) { // si es al menos la fecha y el horario actual
            partido.setEstado(new EnJuego());
            PartidoController controller = new PartidoController();
            PartidoDao dao = new PartidoDao();
            PartidoDto dto = dao.toDto(partido);
            controller.ModificarPartido(dto);
            List<Usuario> notificados = new ArrayList<>();
            partido.getParticipaciones().forEach(participacion -> {
                notificados.add(participacion.getUsuario());
            });
            partido.getObserver().SerNotificado(notificados, new Notificacion("Partido En Juego", "¡El partido ha comenzado!"));
        } else {
            System.out.println("La fecha y hora del partido aún no han llegado.");
        }
    }
    public void CancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
        PartidoController controller = new PartidoController();
        PartidoDao dao = new PartidoDao();
        PartidoDto dto = dao.toDto(partido);
        controller.ModificarPartido(dto);
        List<Usuario> notificados = new ArrayList<>();
        partido.getParticipaciones().forEach(participacion -> {
            notificados.add(participacion.getUsuario());
        });
        partido.getObserver().SerNotificado(notificados, new Notificacion("Partido Cancelado", "¡El organizador ha cancelado este partido."));
    }
    @Override
    public String toString() {
        return "Confirmado";
    }
}
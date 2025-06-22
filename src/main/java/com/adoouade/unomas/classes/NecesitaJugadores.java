package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class NecesitaJugadores implements IEstadoPartido {
    public void ManejarPartido(Partido partido) {
        List<Participacion> participaciones = partido.getParticipaciones();
        if (participaciones.size()>=partido.getCantidadJugadores()) {
            partido.setEstado(new PartidoArmado());
            List<Usuario> notificados = new ArrayList<>();
            participaciones.forEach(participacion -> {
                notificados.add(participacion.getUsuario());
            });
            partido.getObserver().SerNotificado(notificados, new Notificacion("Partido Armado", "Se han conseguido invitar suficientes jugadores para armar el partido ¡confirmá tu asistencia!"));
        } else {
            System.out.println("La cantidad de jugadores invitados no es suficiente.");
        }
    }
    public void CancelarPartido(Partido partido) {
        partido.setEstado(new Cancelado());
        List<Usuario> notificados = new ArrayList<>();
        partido.getParticipaciones().forEach(participacion -> {
            notificados.add(participacion.getUsuario());
        });
        partido.getObserver().SerNotificado(notificados, new Notificacion("Partido Cancelado", "El organizador ha cancelado este partido."));
    }
}
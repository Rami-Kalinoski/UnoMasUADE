package com.adoouade.unomas.frontend;

import com.adoouade.unomas.classes.Confirmado;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PartidosPorJugarseView {
    public PartidosPorJugarseView(CardLayout card, JPanel panelCard, Usuario usuario, PartidoController controller) {
        JPanel porJugarse = new JPanel();
        porJugarse.setLayout(new BorderLayout());

        List<Partido> partidosPendientes = controller.ObtenerPartidos().stream()
                .filter(p -> p.getEstado() instanceof Confirmado &&
                        p.getParticipaciones().stream()
                                .anyMatch(part -> part.getUsuario().getId().equals(usuario.getId()))
                )
                .collect(Collectors.toList());

        // Sección NORTH
        JLabel title = new JLabel("Partidos Por Jugarse");
        porJugarse.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel infoPanel = new JPanel(); infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        partidosPendientes.forEach(partido -> {
            JPanel panel = new JPanel(); panel.setLayout(new GridLayout(3, 1));
            panel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte() + " | " + partido.getUbicacion()));
            panel.add(new JLabel(partido.getFecha() + " - " + partido.getHorario() + " - duración de " + partido.getDuracionMinutos() + " minutos"));
            panel.add(new JLabel(partido.getEmparejador().getEstrategia().toString()));
        });


        // Agregar al card
        panelCard.add(porJugarse, "Partidos Por Jugarse");
    }
}
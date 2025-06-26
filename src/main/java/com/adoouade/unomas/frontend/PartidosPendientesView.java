package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PartidosPendientesView {
    public PartidosPendientesView(CardLayout card, JPanel panelCard, Usuario usuario, PartidoController controller) {
        JPanel pendientes = new JPanel();
        pendientes.setLayout(new BorderLayout());

        List<Partido> partidosPendientes = controller.ObtenerPartidos().stream()
                .filter(p -> p.getParticipaciones().stream()
                        .anyMatch(part ->
                                part.getUsuario().getId().equals(usuario.getId()) &&
                                        part.getEstado() == EstadoParticipacion.PENDIENTE
                        )
                )
                .collect(Collectors.toList());;

        // Sección NORTH
        JLabel title = new JLabel("Partidos Pendientes");
        pendientes.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel infoPanel = new JPanel(); infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        partidosPendientes.forEach(partido -> {
            JPanel panel = new JPanel(); panel.setLayout(new GridLayout(4, 1));
            panel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte() + " | " + partido.getUbicacion()));
            panel.add(new JLabel(partido.getFecha() + " - " + partido.getHorario() + " - duración de " + partido.getDuracionMinutos() + " minutos"));
            panel.add(new JLabel(partido.getEmparejador().getEstrategia().toString()));
            JPanel buttons = new JPanel(); buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            JButton aceptarBtn = new JButton("ACEPTAR");
            JButton rechazarBtn = new JButton("RECHAZAR");
            buttons.add(aceptarBtn); buttons.add(rechazarBtn);
        });


        // Agregar al card
        panelCard.add(pendientes, "Partidos Pendientes");
    }
}
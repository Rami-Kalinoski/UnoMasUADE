package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PartidosFinalizadosView {
    public PartidosFinalizadosView(CardLayout card, JPanel panelCard, Usuario usuario, PartidoController partidoController, UsuarioController usuarioController) {
        JPanel finalizados = new JPanel();
        finalizados.setLayout(new BorderLayout());

        List<Partido> partidosPendientes = partidoController.ObtenerPartidos().stream()
                .filter(p -> p.getParticipaciones().stream()
                        .anyMatch(part ->
                                part.getUsuario().getId().equals(usuario.getId()) &&
                                        part.getEstado() == EstadoParticipacion.PENDIENTE
                        )
                )
                .collect(Collectors.toList());;

        // Sección NORTH
        JLabel title = new JLabel("Partidos Finalizados");
        finalizados.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel infoPanel = new JPanel(); infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        partidosPendientes.forEach(partido -> {
            JPanel panel = new JPanel(); panel.setLayout(new GridLayout(4, 1));
            panel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte() + " | " + partido.getUbicacion()));
            panel.add(new JLabel(partido.getFecha() + " - " + partido.getHorario() + " - duración de " + partido.getDuracionMinutos() + " minutos"));
            panel.add(new JLabel(partido.getEmparejador().getEstrategia().toString()));
            JPanel buttons = new JPanel(); buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            Participacion participacion = usuarioController.ObtenerParticipacion(usuario.getId(), partido.getId());
            JLabel resultadoLabel = new JLabel(participacion.getResultado().toString());
            JButton verReseñasBtn = new JButton("VER RESEÑAS");
            JButton dejarReseñaBtn = new JButton("DEJAR RESEÑA");
            buttons.add(resultadoLabel); buttons.add(verReseñasBtn); buttons.add(dejarReseñaBtn);
        });


        // Agregar al card
        panelCard.add(finalizados, "Partidos Pendientes");
    }
}
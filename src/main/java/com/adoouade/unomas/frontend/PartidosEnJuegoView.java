package com.adoouade.unomas.frontend;

import com.adoouade.unomas.classes.NecesitaJugadores;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PartidosEnJuegoView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel enJuego = new JPanel();
        enJuego.setLayout(new BorderLayout());

        List<Partido> partidosPendientes = partidoController.ObtenerPartidos().stream()
                .filter(p -> p.getEstado() instanceof NecesitaJugadores &&
                        p.getParticipaciones().stream()
                                .anyMatch(part -> part.getUsuario().getId().equals(usuario.getId()))
                )
                .collect(Collectors.toList());

        // Sección NORTH
        JPanel topPanel = new JPanel(); topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JLabel title = new JLabel("Partidos En Juego");
        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> {
            card.show(panelCard, "Visor de Partidos");
        });
        topPanel.add(title); topPanel.add(volverBtn);
        enJuego.add(topPanel, BorderLayout.NORTH);

        // Sección CENTER
        JPanel infoPanel = new JPanel(); infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        partidosPendientes.forEach(partido -> {
            JPanel panel = new JPanel(); panel.setLayout(new GridLayout(3, 1));
            panel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte() + " | " + partido.getUbicacion()));
            panel.add(new JLabel(partido.getFecha() + " - " + partido.getHorario() + " - duración de " + partido.getDuracionMinutos() + " minutos"));
            panel.add(new JLabel(partido.getEmparejador().getEstrategia().toString()));
        });


        // Agregar al card
        panelCard.add(enJuego, "Partidos En Juego");
    }
}
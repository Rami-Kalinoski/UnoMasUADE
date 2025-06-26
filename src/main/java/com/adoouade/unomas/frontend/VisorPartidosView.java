package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class VisorPartidosView {
    public VisorPartidosView(CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel visor = new JPanel();
        visor.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Visor de Partidos");
        visor.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton pendientesBtn = new JButton("Partidos pendientes"); buttons.add(pendientesBtn);
        JButton sinArmarseBtn = new JButton("Partridos sin armarse"); buttons.add(sinArmarseBtn);
        JButton porJugarseBtn = new JButton("Partidos por jugarse"); buttons.add(porJugarseBtn);
        JButton enJuegoBtn = new JButton("Partidos en juego"); buttons.add(enJuegoBtn);
        JButton finalizadosBtn = new JButton("Partidos finalizados"); buttons.add(finalizadosBtn);
        visor.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(visor, "Visor de partidos");
    }
}
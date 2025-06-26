package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class PartidosView {
    public PartidosView(CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel partidos = new JPanel();
        partidos.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Partidos");
        partidos.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton verPerfilBtn = new JButton("Visor de partidos"); buttons.add(verPerfilBtn);
        JButton historialBtn = new JButton("Buscar partidos"); buttons.add(historialBtn);
        JButton partidosBtn = new JButton("Crear partido"); buttons.add(partidosBtn);
        partidos.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(partidos, "Partidos");
    }
}
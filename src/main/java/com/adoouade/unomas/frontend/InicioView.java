package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {
    public InicioView(CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel inicio = new JPanel();
        inicio.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("¡Bienvenido a Uno Más " + usuario.getUsername() + "!");
        inicio.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton verPerfilBtn = new JButton("Ver perfil"); buttons.add(verPerfilBtn);
        JButton historialBtn = new JButton("Historial"); buttons.add(historialBtn);
        JButton partidosBtn = new JButton("Partidos"); buttons.add(partidosBtn);
        inicio.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(inicio, "Inicio");
    }
}
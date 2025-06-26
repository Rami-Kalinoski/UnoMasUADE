package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class BuscarPartidosView {
    public BuscarPartidosView (CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel buscador = new JPanel();
        buscador.setLayout(new BorderLayout());

        // Sección NORHT
        buscador.add( new JLabel("Buscar partido"), BorderLayout.NORTH);

        // Sección CENTER
        JPanel centerPanel = new JPanel(); centerPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel(); topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        ubicacionSet.add(new JLabel("Ubicación")); ubicacionSet.add(new JTextField(usuario.getUbicacion()));
        JButton buscarBtn = new JButton("Buscar");
        topPanel.add(ubicacionSet); topPanel.add(buscarBtn);
        centerPanel.add(topPanel);

        JPanel bottomPanel = new JPanel(); centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));


        centerPanel.add(bottomPanel);

        buscador.add(centerPanel, BorderLayout.CENTER);

        // Agregar al panelCard
        panelCard.add(buscador, "Buscar partidos");
    }
}
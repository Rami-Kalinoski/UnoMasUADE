package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class PartidosView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel partidos = new JPanel();
        partidos.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Partidos");
        partidos.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton visorDePartidosBtn = new JButton("Visor de partidos");
        JButton buscarPartidosBtn = new JButton("Buscar partidos");
        JButton crearPartidoBtn = new JButton("Crear partido");

        visorDePartidosBtn.addActionListener(e ->  {
            card.show(panelCard, "Visor de Partidos");
        });
        buscarPartidosBtn.addActionListener(e ->  {
            BuscarPartidosView buscarPartidosView = new BuscarPartidosView(); buscarPartidosView.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Buscar Partidos");
        });
        crearPartidoBtn.addActionListener(e ->  {
            card.show(panelCard, "Crear Partido");
        });


        buttons.add(visorDePartidosBtn); buttons.add(buscarPartidosBtn); buttons.add(crearPartidoBtn);
        partidos.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(partidos, "Partidos");
    }
}
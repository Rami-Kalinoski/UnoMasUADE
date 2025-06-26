package com.adoouade.unomas.frontend;

import com.adoouade.unomas.UnomasApplication;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class VisorPartidosView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel visor = new JPanel();
        visor.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Visor de Partidos");
        visor.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton pendientesBtn = new JButton("Partidos pendientes");
        JButton sinArmarseBtn = new JButton("Partridos sin armarse");
        JButton porJugarseBtn = new JButton("Partidos por jugarse");
        JButton enJuegoBtn = new JButton("Partidos en juego");
        JButton finalizadosBtn = new JButton("Partidos finalizados");

        pendientesBtn.addActionListener(e -> {
            PartidosPendientesView pantalla = new PartidosPendientesView(); pantalla.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos Pendientes");
        });
        sinArmarseBtn.addActionListener(e -> {
            PartidosSinArmarseView pantalla = new PartidosSinArmarseView(); pantalla.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos Sin Armarse");
        });
        porJugarseBtn.addActionListener(e -> {
            PartidosPorJugarseView pantalla = new PartidosPorJugarseView(); pantalla.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos Por Jugarse");
        });
        enJuegoBtn.addActionListener(e -> {
            PartidosEnJuegoView pantalla = new PartidosEnJuegoView(); pantalla.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos En Juego");
        });
        finalizadosBtn.addActionListener(e -> {
            PartidosFinalizadosView pantalla = new PartidosFinalizadosView(); pantalla.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos Finalizados");
        });

        buttons.add(pendientesBtn); buttons.add(sinArmarseBtn); buttons.add(porJugarseBtn); buttons.add(enJuegoBtn); buttons.add(finalizadosBtn);
        visor.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(visor, "Visor de Partidos");
    }
}
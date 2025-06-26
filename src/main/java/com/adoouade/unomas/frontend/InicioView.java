package com.adoouade.unomas.frontend;

import com.adoouade.unomas.UnomasApplication;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class InicioView extends JFrame {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel inicio = new JPanel();
        inicio.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("¡Bienvenido a Uno Más " + usuario.getUsername()==null ? "" : usuario.getUsername() + "!");
        inicio.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton verPerfilBtn = new JButton("Ver perfil");
        JButton partidosBtn = new JButton("Partidos");
        JButton cerrarSesionBtn = new JButton("Cerrar Sesión");


        verPerfilBtn.addActionListener(e -> {
            PerfilView perfilView = new PerfilView(); perfilView.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Perfil");
        });
        partidosBtn.addActionListener(e -> {
            PartidosView partidosView = new PartidosView(); partidosView.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuario, deporteController);
            card.show(panelCard, "Partidos");
        });
        cerrarSesionBtn.addActionListener(e -> {
            usuarioController.setUsuario(null);
            card.show(panelCard, "Iniciar Sesion");
        });

        buttons.add(verPerfilBtn); buttons.add(partidosBtn); buttons.add(cerrarSesionBtn);
        inicio.add(buttons, BorderLayout.CENTER);

        // Agregar al card
        panelCard.add(inicio, "Inicio");
    }
}
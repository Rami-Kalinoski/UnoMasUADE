package com.adoouade.unomas.frontend;

import com.adoouade.unomas.UnomasApplication;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class IniciarSesionView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel iniciarSesion = new JPanel();
        iniciarSesion.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Iniciar Sesión");
        iniciarSesion.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel formPanel = new JPanel(); formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        JPanel usernameSet = new JPanel(); usernameSet.setLayout(new BoxLayout(usernameSet, BoxLayout.Y_AXIS));
        JLabel usernameLabel = new JLabel("Username"); JTextField usernameField = new JTextField();
        usernameSet.add(usernameLabel); usernameSet.add(usernameField);
        JPanel emailSet = new JPanel(); emailSet.setLayout(new BoxLayout(emailSet, BoxLayout.Y_AXIS));
        JLabel emailLabel = new JLabel("Email"); JTextField emailField = new JTextField();
        emailSet.add(emailLabel); emailSet.add(emailField);
        JPanel passwordSet = new JPanel(); passwordSet.setLayout(new BoxLayout(passwordSet, BoxLayout.Y_AXIS));
        JLabel passwordLabel = new JLabel("Contraseña"); JTextField passwordField = new JTextField();
        passwordSet.add(passwordLabel); passwordSet.add(passwordField);
        formPanel.add(usernameSet); formPanel.add(emailSet); formPanel.add(passwordSet);
        iniciarSesion.add(formPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel buttonsPanel = new JPanel();
        JButton iniciarSesionBtn = new JButton("INICIAR SESIÓN");
        JButton irRegistrarseBtn = new JButton("¿No tenes cuenta? Registrate");

        iniciarSesionBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String contraseña = passwordField.getText();

            try {
                boolean entra = usuarioController.Login(username, email, contraseña);
                if (entra) {
                    Usuario usuarioAux = usuarioController.ObtenerUsuario(username);
                    usuarioController.setUsuario(usuarioAux);
                    JOptionPane.showMessageDialog(null, "¡Inicio de sesión exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    InicioView inicioView = new InicioView();
                    inicioView.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuarioAux, deporteController);
                    card.show(panelCard, "Inicio");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
            }
        });

        irRegistrarseBtn.addActionListener(e -> {
            card.show(panelCard, "Registrarse");
        });

        buttonsPanel.add(iniciarSesionBtn); buttonsPanel.add(irRegistrarseBtn);
        iniciarSesion.add(buttonsPanel, BorderLayout.SOUTH);


        // Agregar al panelCard
        panelCard.add(iniciarSesion, "Iniciar Sesion");
    }
}
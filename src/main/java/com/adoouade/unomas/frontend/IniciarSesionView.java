package com.adoouade.unomas.frontend;

import javax.swing.*;
import java.awt.*;

public class IniciarSesionView {
    public IniciarSesionView(CardLayout card, JPanel panelCard) {
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
        buttonsPanel.add(iniciarSesionBtn); buttonsPanel.add(irRegistrarseBtn);
        iniciarSesion.add(buttonsPanel, BorderLayout.SOUTH);


        // Agregar al panelCard
        panelCard.add(iniciarSesion, "Iniciar Sesion");
    }
}
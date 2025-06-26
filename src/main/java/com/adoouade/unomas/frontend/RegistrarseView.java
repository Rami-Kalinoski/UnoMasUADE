package com.adoouade.unomas.frontend;

import javax.swing.*;
import java.awt.*;

public class RegistrarseView {
    public RegistrarseView(CardLayout card, JPanel panelCard) {
        JPanel registrarse = new JPanel();
        registrarse.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Registrarse");
        registrarse.add(title, BorderLayout.NORTH);

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

        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        JLabel ubicacionLabel = new JLabel("Ubicación"); JTextField ubicacionField = new JTextField();
        ubicacionSet.add(ubicacionLabel); ubicacionSet.add(ubicacionField);

        JPanel notificacionesSet = new JPanel(); notificacionesSet.setLayout(new BoxLayout(notificacionesSet, BoxLayout.Y_AXIS));
        JLabel notificacionesLabel = new JLabel("Notificaciones por");
        String[] tipos = {"PUSH", "EMAIL"};
        JComboBox<String> comboBox = new JComboBox<>(tipos);
        comboBox.setSelectedItem("PUSH");
        notificacionesSet.add(notificacionesLabel); notificacionesSet.add(comboBox);

        formPanel.add(usernameSet); formPanel.add(emailSet); formPanel.add(passwordSet); formPanel.add(ubicacionSet); formPanel.add(notificacionesSet);
        registrarse.add(formPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        JButton iniciarSesionBtn = new JButton("REGISTRARSE");
        JButton irRegistrarseBtn = new JButton("¿Ya tenes cuenta? Inicia Sesión");
        buttonsPanel.add(iniciarSesionBtn); buttonsPanel.add(irRegistrarseBtn);
        registrarse.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar al panelCard
        panelCard.add(registrarse, "Registrarse");
    }
}
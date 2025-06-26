package com.adoouade.unomas.frontend;

import com.adoouade.unomas.UnomasApplication;
import com.adoouade.unomas.classes.Email;
import com.adoouade.unomas.classes.Emparejador;
import com.adoouade.unomas.classes.Push;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.enums.Notificaciones;
import com.adoouade.unomas.interfaces.IEstrategiaNotificador;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class RegistrarseView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
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
        JButton registrarseBtn = new JButton("REGISTRARSE");
        JButton irIniciarSesion = new JButton("¿Ya tenes cuenta? Inicia Sesión");

        registrarseBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String contraseña = passwordField.getText();
            String ubicacion = ubicacionField.getText();
            String notificacion = (String) comboBox.getSelectedItem();

            try {
                Usuario usuarioAux = usuarioController.CrearUsuario(new UsuarioDto(username, email, contraseña, ubicacion, notificacion));
                if (usuarioAux!=null) {
                    usuarioController.setUsuario(usuarioAux);
                    JOptionPane.showMessageDialog(null, "¡Registro exitoso!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    //UnomasApplication.crearPantallasPersonalizadas(card, panelCard, parent, usuarioController, partidoController, usuarioAux, deporteController);
                    InicioView inicioView = new InicioView();
                    inicioView.CrearPantalla(card, panelCard, parent, usuarioController, partidoController, usuarioAux, deporteController);
                    card.show(panelCard, "Inicio");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
            }
        });

        irIniciarSesion.addActionListener(e -> {
            card.show(panelCard, "Iniciar Sesion");
        });


        buttonsPanel.add(registrarseBtn); buttonsPanel.add(irIniciarSesion);
        registrarse.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar al panelCard
        panelCard.add(registrarse, "Registrarse");
    }
}
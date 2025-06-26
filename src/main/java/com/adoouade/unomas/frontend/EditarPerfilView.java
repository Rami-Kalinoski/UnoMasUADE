package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class EditarPerfilView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel editarPerfil = new JPanel();
        editarPerfil.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Perfil");
        editarPerfil.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel formPanel = new JPanel();
        JPanel usernameSet = new JPanel(); usernameSet.setLayout(new BoxLayout(usernameSet, BoxLayout.Y_AXIS));
        JLabel usernameLabel = new JLabel("Username"); JTextField usernameField = new JTextField(); usernameField.setText(usuario.getUsername());
        usernameSet.add(usernameLabel); usernameSet.add(usernameField);

        JPanel emailSet = new JPanel(); emailSet.setLayout(new BoxLayout(emailSet, BoxLayout.Y_AXIS));
        JLabel emailLabel = new JLabel("Email"); JTextField emailField = new JTextField(); emailField.setText(usuario.getEmail());
        emailSet.add(emailLabel); emailSet.add(emailField);

        JPanel passwordSet = new JPanel(); passwordSet.setLayout(new BoxLayout(passwordSet, BoxLayout.Y_AXIS));
        JLabel passwordLabel = new JLabel("Contraseña"); JTextField passwordField = new JTextField(); passwordField.setText(usuario.getContraseña());
        passwordSet.add(passwordLabel); passwordSet.add(passwordField);

        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        JLabel ubicacionLabel = new JLabel("Ubicación"); JTextField ubicacionField = new JTextField(); ubicacionField.setText(usuario.getUbicacion());
        ubicacionSet.add(ubicacionLabel); ubicacionSet.add(ubicacionField);

        JPanel notificacionesSet = new JPanel(); notificacionesSet.setLayout(new BoxLayout(notificacionesSet, BoxLayout.Y_AXIS));
        JLabel notificacionesLabel = new JLabel("Notificaciones por");
        String[] tipos = {"PUSH", "EMAIL"};
        JComboBox<String> comboBox = new JComboBox<>(tipos);
        comboBox.setSelectedItem(usuario.getNotificaciones());
        notificacionesSet.add(notificacionesLabel); notificacionesSet.add(comboBox);

        formPanel.add(usernameSet); formPanel.add(emailSet); formPanel.add(passwordSet); formPanel.add(ubicacionSet); formPanel.add(notificacionesSet);
        editarPerfil.add(formPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JButton guardarBtn = new JButton("GUARDAR");
        JButton cancelarBtn = new JButton("CANCELAR");

        guardarBtn.addActionListener(e -> {
            String username = usernameField.getText();
            String email = emailField.getText();
            String contraseña = passwordField.getText();
            String ubicacion = ubicacionField.getText();
            String notificacion = (String) comboBox.getSelectedItem();

            try {
                boolean modificado = usuarioController.ModificarUsuario(new UsuarioDto(usuario.getId(), username, email, contraseña, ubicacion, notificacion));
                if (modificado) {
                    JOptionPane.showMessageDialog(null, "¡Modificación exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    card.show(panelCard, "Perfil");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
            }

        });

        cancelarBtn.addActionListener(e -> {
            card.show(panelCard, "Perfil");
        });


        buttonsPanel.add(guardarBtn); buttonsPanel.add(cancelarBtn);
        editarPerfil.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar al panelCard
        panelCard.add(editarPerfil, "Editar Perfil");
    }
}
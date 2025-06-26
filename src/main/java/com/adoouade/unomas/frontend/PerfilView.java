package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PerfilView {
    public PerfilView (CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel perfil = new JPanel();
        perfil.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel(usuario.getUsername());
        perfil.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel centerPanel = new JPanel(); centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel deportesPanel = new JPanel(); deportesPanel.setLayout(new BoxLayout(deportesPanel, BoxLayout.Y_AXIS));
        List<UsuarioDeporte> usuarioDeportes = usuario.getUsuarioDeportes();
        if (usuarioDeportes!=null) {
            usuarioDeportes.forEach(ud -> {
                JPanel panel = new JPanel(); panel.setLayout(new GridLayout(3, 2));
                panel.add(new JLabel(ud.getDeporte().getNombre())); panel.add(new JPanel());
                panel.add(new JLabel(ud.isFavorito() ? "favorito" : "no favorito")); panel.add(new JPanel());
                panel.add(new JLabel(ud.getNivel().toString()));

                JButton editarBtn = new JButton("EDITAR");
                panel.add(editarBtn);

                deportesPanel.add(panel);
            });
        }

        JButton nuevoDeporte = new JButton("Configurar Nuevo Deporte");

        centerPanel.add(deportesPanel); centerPanel.add(nuevoDeporte);

        perfil.add(centerPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel southPanel = new JPanel(); southPanel.setLayout(new GridLayout(1, 2));
        JPanel dataPanel = new JPanel(); dataPanel.setLayout(new GridLayout(2, 1));
        JLabel email = new JLabel("Email: " + usuario.getEmail());
        JLabel password = new JLabel("Contraseña: " + usuario.getContraseña());
        dataPanel.add(email); dataPanel.add(password);
        southPanel.add(dataPanel);

        JButton editarPerfilBtn = new JButton("EDITAR");
        southPanel.add(editarPerfilBtn);

        perfil.add(southPanel, BorderLayout.SOUTH);

        // Agregar al card
        panelCard.add(perfil, "Perfil");
    }
}
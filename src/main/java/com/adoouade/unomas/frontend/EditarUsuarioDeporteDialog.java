package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dao.DeporteDao;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;

import javax.swing.*;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class EditarUsuarioDeporteDialog extends JDialog {
    public EditarUsuarioDeporteDialog(JFrame parent, UsuarioController usuarioController, UsuarioDeporte usuarioDeporte) {
        super(parent, "Editar usuario", true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Usuario usuario = usuarioController.getUsuario();

        JPanel deportePanel = new JPanel();
        deportePanel.setLayout(new BoxLayout(deportePanel, BoxLayout.Y_AXIS));
        deportePanel.add(new JLabel("Deporte seleccionado"));
        deportePanel.add(new JLabel(usuarioDeporte.getDeporte().getNombre()));

        add(deportePanel);

        JPanel favoritoPanel = new JPanel(); favoritoPanel.setLayout(new BoxLayout(favoritoPanel, BoxLayout.Y_AXIS));
        favoritoPanel.add(new JLabel("Favorito"));
        JPanel favoritoBtnsPanel = new JPanel(); favoritoBtnsPanel.setLayout(new BoxLayout(favoritoBtnsPanel, BoxLayout.X_AXIS));
        JToggleButton botonSi = new JToggleButton("Sí");
        JToggleButton botonNo = new JToggleButton("No");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(botonSi);
        grupo.add(botonNo);
        favoritoBtnsPanel.add(botonSi); favoritoBtnsPanel.add(botonNo);
        add(favoritoPanel);

        JPanel nivelPanel = new JPanel(); nivelPanel.setLayout(new BoxLayout(nivelPanel, BoxLayout.Y_AXIS));
        nivelPanel.add(new JLabel("Nivel"));
        String[] niveles = {"PRINCIPIANTE", "INTERMEDIO", "AVANZADO"};
        JComboBox<String> comboBoxNivel = new JComboBox<>(niveles);
        comboBoxNivel.setSelectedItem("PRINCIPIANTE");
        add(nivelPanel);

        JPanel buttonsPanel = new JPanel(); nivelPanel.setLayout(new BoxLayout(nivelPanel, BoxLayout.X_AXIS));
        JButton guardarBtn = new JButton("GUARDAR");
        JButton cancelarBtn = new JButton("CANCELAR");

        guardarBtn.addActionListener(e ->  {
            String nivelName = comboBoxNivel.getSelectedItem().toString();
            String favorito = null;
            for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();) {
                AbstractButton boton = buttons.nextElement();
                if (boton.isSelected()) {
                    favorito = boton.getText();
                    break;
                }
            }
            boolean isFavorito = favorito.equals("Sí");

            try {
                boolean modificado = usuarioController.ModificarUsuarioDeporte(new UsuarioDeporteDto(usuario.getId(), usuarioDeporte.getDeporte().getId(), Nivel.valueOf(nivelName), isFavorito));
                if (modificado) {
                    JOptionPane.showMessageDialog(null, "¡Modificación del deporte exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> {
            this.dispose();
        });

        buttonsPanel.add(guardarBtn); buttonsPanel.add(cancelarBtn);
        add(buttonsPanel);
    }
}
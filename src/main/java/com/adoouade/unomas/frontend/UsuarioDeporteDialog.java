package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.DeporteController;
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

public class UsuarioDeporteDialog extends JDialog {
    public UsuarioDeporteDialog(JFrame parent, UsuarioController usuarioController, DeporteController deporteController) {
        super(parent, "Agregar deporte", true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JPanel deportePanel = new JPanel(); deportePanel.setLayout(new BoxLayout(deportePanel, BoxLayout.Y_AXIS));
        deportePanel.add(new JLabel("Seleccionar deporte"));

        List<Deporte> todosLosDeportes = deporteController.getDeporteDao().obtenerDeportes();
        List<UsuarioDeporte> todosLosUsuarioDeporte = usuarioController.ObtenerUsuarioDeportes();
        Usuario usuario = usuarioController.getUsuario();
        List<Deporte> noRegistrados = todosLosDeportes.stream()
                .filter(deporte -> todosLosUsuarioDeporte.stream()
                        .noneMatch(ud -> ud.getUsuario().equals(usuario) && ud.getDeporte().equals(deporte)))
                .collect(Collectors.toList());
        String[] nombres = noRegistrados.stream()
                .map(Deporte::getNombre)  // suponiendo que tenés getNombre()
                .toArray(String[]::new);
        JComboBox<String> comboBoxDeporte = new JComboBox<>(nombres);
        comboBoxDeporte.setSelectedItem(nombres[0]);

        deportePanel.add(comboBoxDeporte);
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
            String deporteName = comboBoxDeporte.getSelectedItem().toString();
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
            Deporte deporteSeleccionado = null;
            for (Deporte d : noRegistrados) {
                if (d.getNombre().equals(deporteName)) {
                    deporteSeleccionado = d;
                    break;
                }
            }

            try {
                UsuarioDeporte usuarioDeporte = usuarioController.CrearUsuarioDeporte(new UsuarioDeporteDto(usuario.getId(), deporteSeleccionado.getId(), Nivel.valueOf(nivelName), isFavorito));
                if (usuarioDeporte!=null) {
                    JOptionPane.showMessageDialog(null, "¡Configuración del deporte exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
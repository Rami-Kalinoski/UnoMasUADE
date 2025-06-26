package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.ReseñaDto;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class DejarReseñaDialog extends JDialog {
    public DejarReseñaDialog (JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Partido partido) {
        super(parent, "Dejar reseña", true);
        setLayout(new BorderLayout());
        Usuario usuario = usuarioController.getUsuario();
        add(new JLabel("Dejar Reseña"), BorderLayout.NORTH);

        JPanel form = new JPanel(); form.setLayout(new BoxLayout(form, BoxLayout.Y_AXIS));
        JPanel comentarioSet = new JPanel(); comentarioSet.setLayout(new BoxLayout(comentarioSet, BoxLayout.Y_AXIS)); comentarioSet.add(new JLabel("Comentario"));
        JTextField comentarioField = new JTextField(); comentarioSet.add(comentarioField);
        JPanel calificacionSet = new JPanel(); calificacionSet.setLayout(new BoxLayout(calificacionSet, BoxLayout.Y_AXIS)); calificacionSet.add(new JLabel("Calificación"));
        JTextField calificacionField = new JTextField(); calificacionSet.add(calificacionField);
        form.add(comentarioSet); form.add(calificacionSet);
        add(form, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JButton save = new JButton("GUARDAR");
        JButton cancelar = new JButton("CANCELAR");

        save.addActionListener(e -> {
            try {
                String comentario = comentarioField.getText();
                int calificacion = Integer.parseInt(calificacionField.getText());
                Reseña reseña = partidoController.CrearReseña(new ReseñaDto(usuario.getId(), partido.getId(), comentario, calificacion));
                JOptionPane.showMessageDialog(null, "¡Reseña publicada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "Error en el formulario", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });
        cancelar.addActionListener(e -> {
            this.dispose();
        });

        buttonsPanel.add(save); buttonsPanel.add(cancelar);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
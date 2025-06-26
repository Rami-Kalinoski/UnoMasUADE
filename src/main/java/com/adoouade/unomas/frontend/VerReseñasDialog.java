package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.ReseñaDto;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Reseña;
import com.adoouade.unomas.model.Usuario;

import javax.sound.sampled.BooleanControl;
import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;
import java.util.List;

public class VerReseñasDialog extends JDialog {
    public VerReseñasDialog (JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Partido partido) {
        super(parent, "Reseñas del Partido Seleccionado", true);
        setLayout(new BorderLayout());
        Usuario usuario = usuarioController.getUsuario();
        add(new JLabel("Reseñas del Partido Seleccionado"), BorderLayout.NORTH);

        JPanel resultadosPanel = new JPanel(); resultadosPanel.setLayout(new BoxLayout(resultadosPanel, BoxLayout.Y_AXIS));
        List<Reseña> reseñas = partidoController.ObtenerReseñas().stream()
                .filter(reseña -> reseña.getPartido() != null && reseña.getPartido().getId().equals(partido.getId()))
                .collect(Collectors.toList());;
        reseñas.forEach(reseña -> {
            JPanel item = new JPanel(); item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
            item.add(new JLabel("Usuario: " + reseña.getUsuario().getUsername()));
            item.add(new JLabel("Calificación: " + reseña.getCalificacion()));
            item.add(new JLabel("Comentario: " + reseña.getComentario()));
            resultadosPanel.add(item);
        });

        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        JButton volver = new JButton("VOLVER");
        volver.addActionListener(e -> {
            this.dispose();
        });

        buttonsPanel.add(volver);
        add(buttonsPanel, BorderLayout.SOUTH);
    }
}
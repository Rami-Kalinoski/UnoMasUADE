package com.adoouade.unomas.frontend;

import com.adoouade.unomas.dao.DeporteDao;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

public class InfoPartidoDialog extends JDialog {
    public InfoPartidoDialog(JFrame parent, Partido partido) {
        super(parent, "Información sobre el partido seleccionado", true);
        setLayout(new BorderLayout());

        // Sección NORHT
        add(new JLabel("Información sobre el partido seleccionado"), BorderLayout.NORTH);

        // Sección CENTER
        JPanel centerPanel = new JPanel(); centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(new JLabel("Partido " + partido.getId()));
        centerPanel.add(new JLabel("Deporte: " + partido.getDeporte().getNombre()));
        centerPanel.add(new JLabel("Ubicación: " + partido.getUbicacion()));
        centerPanel.add(new JLabel("Fecha y hora: " + partido.getFecha() + " a las " + partido.getHorario()));
        centerPanel.add(new JLabel("Emparejamiento: " + partido.getEmparejador().getEstrategia().toString()));

        // Sección SOUTH
        JButton aceptarBtn = new JButton("Aceptar");
        aceptarBtn.addActionListener(e -> {
            this.dispose();
        });
        add(aceptarBtn, BorderLayout.SOUTH);
    }
}
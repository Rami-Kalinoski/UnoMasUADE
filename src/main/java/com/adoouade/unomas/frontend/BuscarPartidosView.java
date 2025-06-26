package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
import jakarta.mail.Part;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuscarPartidosView {
    public void CrearPantalla (CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel buscador = new JPanel();
        buscador.setLayout(new BorderLayout());

        // Sección NORHT
        buscador.add( new JLabel("Buscar partido"), BorderLayout.NORTH);

        // Sección CENTER
        JPanel centerPanel = new JPanel(); centerPanel.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel(); topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        ubicacionSet.add(new JLabel("Ubicación")); JTextField busqueda = new JTextField(usuario.getUbicacion()); ubicacionSet.add(busqueda);
        JButton buscarBtn = new JButton("Buscar");
        topPanel.add(ubicacionSet);

        JPanel bottomPanel = new JPanel(); centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        buscarBtn.addActionListener(e -> {
            String buscado = busqueda.getText();
            List<Partido> resultados = partidoController.ObtenerPartidos().stream()
                    .filter(p -> buscado.equalsIgnoreCase(p.getUbicacion()))
                    .toList();
            resultados.forEach(partido -> {
                JPanel itemPanel = new JPanel(); itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
                itemPanel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte().getNombre() + " | " + partido.getUbicacion()));
                JButton infoBtn = new JButton("Información");
                infoBtn.addActionListener(e1 -> {
                    InfoPartidoDialog info = new InfoPartidoDialog(parent, partido);
                });
                JButton joinBtn = new JButton("UNIRSE");
                joinBtn.addActionListener(e1 -> {
                    try {
                        UsuarioDeporte ud = usuarioController.ObtenerUsuarioDeporte(usuario.getId(), partido.getId());
                        boolean cumple = partido.getEmparejador().getEstrategia().Cumple(ud);
                        if (cumple) {
                            Participacion participacion = usuarioController.ObtenerParticipacion(usuario.getId(), partido.getId());
                            if (participacion!=null) {
                                JOptionPane.showMessageDialog(null, "Ya estabas unido a este partido.", "Participación ya existente", JOptionPane.WARNING_MESSAGE);
                            } else {
                                participacion = usuarioController.CrearParticipacion(new ParticipacionDto(usuario.getId(), partido.getId(), EstadoParticipacion.ACEPTADA, Resultado.INDEFINIDO));
                                JOptionPane.showMessageDialog(null, "¡Unión Exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No cumples con los requisitos del emparejamiento del partido.", "No te puedes unir", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(null, "Ocurrió un error en el servidor. Intente nuevamente más tarde.", "Error del servidor", JOptionPane.WARNING_MESSAGE);
                    }
                });

                itemPanel.add(infoBtn); itemPanel.add(joinBtn);
                bottomPanel.add(itemPanel);
            });
        });


        topPanel.add(buscarBtn);
        centerPanel.add(topPanel);


        centerPanel.add(bottomPanel);

        buscador.add(centerPanel, BorderLayout.CENTER);

        // Agregar al panelCard
        panelCard.add(buscador, "Buscar Partidos");
    }
}
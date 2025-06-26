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

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class PartidosPendientesView {
    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel pendientes = new JPanel();
        pendientes.setLayout(new BorderLayout());

        List<Partido> partidosPendientes = partidoController.ObtenerPartidos().stream()
                .filter(p -> p.getParticipaciones().stream()
                        .anyMatch(part ->
                                part.getUsuario().getId().equals(usuario.getId()) &&
                                        part.getEstado() == EstadoParticipacion.PENDIENTE
                        )
                )
                .collect(Collectors.toList());;

        // Sección NORTH
        JPanel topPanel = new JPanel(); topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        JLabel title = new JLabel("Partidos Pendientes");
        JButton volverBtn = new JButton("Volver");
        volverBtn.addActionListener(e -> {
            card.show(panelCard, "Visor de Partidos");
        });
        topPanel.add(title); topPanel.add(volverBtn);
        pendientes.add(topPanel, BorderLayout.NORTH);

        // Sección CENTER
        JPanel infoPanel = new JPanel(); infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        partidosPendientes.forEach(partido -> {
            JPanel panel = new JPanel(); panel.setLayout(new GridLayout(4, 1));
            panel.add(new JLabel("Partido " + partido.getId() + " | " + partido.getDeporte() + " | " + partido.getUbicacion()));
            panel.add(new JLabel(partido.getFecha() + " - " + partido.getHorario() + " - duración de " + partido.getDuracionMinutos() + " minutos"));
            panel.add(new JLabel(partido.getEmparejador().getEstrategia().toString()));
            JPanel buttons = new JPanel(); buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
            JButton aceptarBtn = new JButton("ACEPTAR");
            JButton rechazarBtn = new JButton("RECHAZAR");
            Participacion participacion = usuarioController.ObtenerParticipacion(usuario.getId(), partido.getId());

            aceptarBtn.addActionListener(e ->  {
                if (!participacion.getEstado().equals(EstadoParticipacion.ACEPTADA)) {
                    usuarioController.ModificarParticipacion(new ParticipacionDto(participacion.getId(), usuario.getId(), partido.getId(), EstadoParticipacion.ACEPTADA.name(), Resultado.INDEFINIDO.name()));
                    JOptionPane.showMessageDialog(null, "¡Invitación aceptada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya habías aceptado esta invitación.", "Error", JOptionPane.WARNING_MESSAGE);
                }

            });
            rechazarBtn.addActionListener(e ->  {
                if (!participacion.getEstado().equals(EstadoParticipacion.RECHAZADA)) {
                    usuarioController.ModificarParticipacion(new ParticipacionDto(participacion.getId(), usuario.getId(), partido.getId(), EstadoParticipacion.RECHAZADA.name(), Resultado.INDEFINIDO.name()));
                    JOptionPane.showMessageDialog(null, "¡Invitación rechazada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya habías rechazado esta invitación.", "Error", JOptionPane.WARNING_MESSAGE);
                }

            });


            buttons.add(aceptarBtn); buttons.add(rechazarBtn);
        });


        // Agregar al card
        panelCard.add(pendientes, "Partidos Pendientes");
    }
}
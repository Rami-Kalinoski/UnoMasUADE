package com.adoouade.unomas.frontend;

import com.adoouade.unomas.classes.NecesitaJugadores;
import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dao.DeporteDao;
import com.adoouade.unomas.dto.PartidoDto;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.interfaces.IEstrategiaEmparejamiento;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class CrearPartidoView {
    private IEstrategiaEmparejamiento emparejamientoSeleccionado;

    public void CrearPantalla(CardLayout card, JPanel panelCard, JFrame parent, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
        JPanel crearPartido = new JPanel();
        crearPartido.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Crear partido");
        crearPartido.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel formPanel = new JPanel(); formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));

        Deporte[] deportes = deporteController.getDeporteDao().obtenerDeportes().toArray(new Deporte[0]);
        String[] nombres = deporteController.getDeporteDao().obtenerDeportes().stream().map(Deporte::getNombre).toArray(String[]:: new);
        JPanel deporteSet = new JPanel(); deporteSet.setLayout(new BoxLayout(deporteSet, BoxLayout.Y_AXIS));
        JLabel deporteLabel = new JLabel("Deporte"); JComboBox comboBoxDeportes = new JComboBox(nombres); comboBoxDeportes.setSelectedItem(nombres[0]);
        deporteSet.add(deporteLabel); deporteSet.add(comboBoxDeportes);

        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        JLabel ubicacionLabel = new JLabel("Ubicación"); JTextField ubicacionField = new JTextField();
        ubicacionSet.add(ubicacionLabel); ubicacionSet.add(ubicacionField);

        JPanel fechaSet = new JPanel(); fechaSet.setLayout(new BoxLayout(fechaSet, BoxLayout.Y_AXIS));
        JLabel fechaLabel = new JLabel("Fecha"); JTextField fechaField = new JTextField();
        fechaSet.add(fechaLabel); fechaSet.add(fechaField);

        JPanel horarioSet = new JPanel(); horarioSet.setLayout(new BoxLayout(horarioSet, BoxLayout.Y_AXIS));
        JLabel horarioLabel = new JLabel("Horario"); JTextField horarioField = new JTextField();
        horarioSet.add(horarioLabel); horarioSet.add(horarioField);

        JPanel cantidadSet = new JPanel(); cantidadSet.setLayout(new BoxLayout(cantidadSet, BoxLayout.Y_AXIS));
        JLabel cantidadLabel = new JLabel("Cantidad de Jugadores"); JTextField cantidadField = new JTextField();
        cantidadSet.add(cantidadLabel); cantidadSet.add(cantidadField);

        JPanel duracionSet = new JPanel(); duracionSet.setLayout(new BoxLayout(duracionSet, BoxLayout.Y_AXIS));
        JLabel duracionLabel = new JLabel("Duración en Minutos"); JTextField duracionField = new JTextField();
        duracionSet.add(duracionLabel); duracionSet.add(duracionField);

        JButton emparejamientoBtn = new JButton("Armar Emparejamiento");

        emparejamientoBtn.addActionListener(e -> {
            ArmarEmparejamientoDialog dialogo = new ArmarEmparejamientoDialog(parent);
            emparejamientoSeleccionado = dialogo.getEstrategiaSeleccionada();
        });

        formPanel.add(deporteSet); formPanel.add(fechaSet); formPanel.add(duracionSet); formPanel.add(ubicacionSet); formPanel.add(emparejamientoBtn);
        crearPartido.add(formPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        JButton crearBtn = new JButton("CREAR");
        JButton cancelarBtn = new JButton("CANCELAR");

        crearBtn.addActionListener(e -> {
            String deporteName = comboBoxDeportes.getSelectedItem().toString();
            Deporte deporteSeleccionado = null;
            for (Deporte d : deportes) {
                if (d.getNombre().equals(deporteName)) {
                    deporteSeleccionado = d;
                    break;
                }
            }

            boolean excepcion = false;
            try {
                String ubicacion = ubicacionField.getText();
                String fecha = fechaField.getText();
                String horario = horarioField.getText();
                IEstadoPartido estado = new NecesitaJugadores();
                IEstrategiaEmparejamiento emparejador = emparejamientoSeleccionado;
                LocalDate date = LocalDate.parse(fecha);
                LocalTime time = LocalTime.parse(horario);
                int cantidadJugadores = Integer.parseInt(cantidadField.getText());
                int duracionMinutos = Integer.parseInt(duracionField.getText());
                if (emparejador==null) throw new Exception();

                Partido partido = partidoController.CrearPartido(new PartidoDto(usuario.getId(), deporteSeleccionado.getId(), ubicacion, date, time, estado, emparejador.toString(), cantidadJugadores, duracionMinutos));
                emparejador.Emparejar(partido);

                JOptionPane.showMessageDialog(null, "¡Partido creado exitosamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception exception) {
                excepcion = true;
                JOptionPane.showMessageDialog(null, "Error en el formulario", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        cancelarBtn.addActionListener(e -> {
            card.show(panelCard, "Partidos");
        });

        buttonsPanel.add(crearBtn); buttonsPanel.add(cancelarBtn);
        crearPartido.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar al panelCard
        panelCard.add(crearPartido, "Crear Partido");
    }
}
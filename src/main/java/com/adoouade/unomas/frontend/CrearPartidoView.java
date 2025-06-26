package com.adoouade.unomas.frontend;

import com.adoouade.unomas.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class CrearPartidoView {
    private String emparejamientoSeleccionado;

    public CrearPartidoView(CardLayout card, JPanel panelCard, Usuario usuario) {
        JPanel crearPartido = new JPanel();
        crearPartido.setLayout(new BorderLayout());

        // Sección NORTH
        JLabel title = new JLabel("Crear partido");
        crearPartido.add(title, BorderLayout.NORTH);

        // Sección CENTER
        JPanel formPanel = new JPanel(); formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        JPanel deporteSet = new JPanel(); deporteSet.setLayout(new BoxLayout(deporteSet, BoxLayout.Y_AXIS));
        JLabel deporteLabel = new JLabel("Deporte"); JTextField deporteField = new JTextField();
        deporteSet.add(deporteLabel); deporteSet.add(deporteField);

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

        JButton emparejamientoBtn = new JButton("Armar Emparejamiento");

        formPanel.add(deporteSet); formPanel.add(fechaSet); formPanel.add(cantidadSet); formPanel.add(ubicacionSet); formPanel.add(emparejamientoBtn);
        crearPartido.add(formPanel, BorderLayout.CENTER);

        // Sección SOUTH
        JPanel buttonsPanel = new JPanel(); buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        JButton crearBtn = new JButton("CREAR");
        JButton cancelarBtn = new JButton("CANCELAR");
        buttonsPanel.add(crearBtn); buttonsPanel.add(cancelarBtn);
        crearPartido.add(buttonsPanel, BorderLayout.SOUTH);

        // Agregar al panelCard
        panelCard.add(crearPartido, "Crear Partido");
    }
}
package com.adoouade.unomas.frontend;

import com.adoouade.unomas.classes.PorCercania;
import com.adoouade.unomas.classes.PorHistorial;
import com.adoouade.unomas.classes.PorNivel;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.interfaces.IEstrategiaEmparejamiento;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;

@Getter
public class ArmarEmparejamientoDialog extends JDialog {
    private IEstrategiaEmparejamiento estrategiaSeleccionada;

    public ArmarEmparejamientoDialog(JFrame parent) {
        super(parent, "Armar emparejamiento", true);
        setLayout(new BorderLayout());

        // Sección NORTH
        JPanel comboSet = new JPanel(); comboSet.setLayout(new BoxLayout(comboSet, BoxLayout.Y_AXIS));
        comboSet.add(new JLabel("Estrategia de emparejamiento"));

        String[] estrategias = {"Por Cercania", "Por Nivel", "Por Historial"};
        JComboBox<String> comboBox = new JComboBox<>(estrategias);
        comboBox.setSelectedItem("Por Cercania");

        JPanel cards = new JPanel(new CardLayout());

        JPanel porCercaniaPanel = new JPanel(); porCercaniaPanel.setLayout(new BoxLayout(porCercaniaPanel, BoxLayout.Y_AXIS));
        JPanel ubicacionSet = new JPanel(); ubicacionSet.setLayout(new BoxLayout(ubicacionSet, BoxLayout.Y_AXIS));
        ubicacionSet.add(new JLabel("Ubicación"));
        JTextField ubicacionField = new JTextField();
        ubicacionSet.add(ubicacionField);
        cards.add(porCercaniaPanel, "Por Cercania");

        JPanel porNivelPanel = new JPanel(); porNivelPanel.setLayout(new BoxLayout(porNivelPanel, BoxLayout.Y_AXIS));
        JPanel minNivPanel = new JPanel(); minNivPanel.setLayout(new BoxLayout(minNivPanel, BoxLayout.Y_AXIS)); minNivPanel.add(new JLabel("Nivel Mínimo"));
        JTextField minNivField = new JTextField(); minNivPanel.add(minNivField);
        JPanel maxNivPanel = new JPanel(); maxNivPanel.setLayout(new BoxLayout(maxNivPanel, BoxLayout.Y_AXIS)); maxNivPanel.add(new JLabel("Nivel Máximo"));
        JTextField maxNivField = new JTextField(); maxNivPanel.add(maxNivField);
        cards.add(porNivelPanel, "Por Nivel");

        JPanel porHistorialPanel = new JPanel(); porHistorialPanel.setLayout(new BoxLayout(porHistorialPanel, BoxLayout.Y_AXIS));
        JPanel minGanPanel = new JPanel(); minGanPanel.setLayout(new BoxLayout(minGanPanel, BoxLayout.Y_AXIS)); minGanPanel.add(new JLabel("Mínimos Ganados"));
        JTextField minGanField = new JTextField(); minGanPanel.add(minGanField);
        JPanel maxGanPanel = new JPanel(); maxGanPanel.setLayout(new BoxLayout(maxGanPanel, BoxLayout.Y_AXIS)); maxGanPanel.add(new JLabel("Mínimos Ganados"));
        JTextField maxGanField = new JTextField(); maxGanPanel.add(maxGanField);
        JPanel minPerPanel = new JPanel(); minPerPanel.setLayout(new BoxLayout(minPerPanel, BoxLayout.Y_AXIS)); minPerPanel.add(new JLabel("Mínimos Ganados"));
        JTextField minPerField = new JTextField(); minPerPanel.add(minPerField);
        JPanel maxPerPanel = new JPanel(); maxPerPanel.setLayout(new BoxLayout(maxPerPanel, BoxLayout.Y_AXIS)); maxPerPanel.add(new JLabel("Mínimos Ganados"));
        JTextField maxPerField = new JTextField(); maxPerPanel.add(maxPerField);
        porHistorialPanel.add(minGanPanel); porHistorialPanel.add(maxGanPanel); porHistorialPanel.add(minPerPanel); porHistorialPanel.add(maxPerPanel);
        cards.add(porHistorialPanel, "Por Historial");

        comboBox.addActionListener(e -> {
            String seleccionado = (String) comboBox.getSelectedItem();
            CardLayout cl = (CardLayout)(cards.getLayout());
            if (seleccionado.equals("Por Cercania")) {
                cl.show(cards, "Por Cercania");
            } else if (seleccionado.equals("Por Nivel")) {
                cl.show(cards, "Por Nivel");
            } else {
                cl.show(cards, "Por Historial");
            }
        });

        // Sección SOUTH
        JPanel southPanel = new JPanel(); southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
        JButton guardarBtn = new JButton("GUARDAR");
        guardarBtn.addActionListener(e -> {
            String opcionSeleccionada = comboBox.getSelectedItem().toString();
            if (opcionSeleccionada.equals("Por Cercania")) {
                String ubicacion = ubicacionField.getText();
                estrategiaSeleccionada = new PorCercania(ubicacion);
            } else if (opcionSeleccionada.equals("Por Nivel")) {
                String nivelMinimo = minNivField.getText();
                String nivelMaximo = maxNivField.getText();
                estrategiaSeleccionada = new PorNivel(Nivel.valueOf(nivelMinimo), Nivel.valueOf(nivelMaximo));
            } else {
                int minimosGanados = Integer.parseInt(minGanField.getText());
                int maximosGanados = Integer.parseInt(maxGanField.getText());
                int minimosPerdidos = Integer.parseInt(minPerField.getText());
                int maximosPerdidos = Integer.parseInt(maxPerField.getText());
                estrategiaSeleccionada = new PorHistorial(minimosGanados, maximosGanados, minimosPerdidos, maximosPerdidos);
            }
            this.dispose();
        });

        JButton cancelarBtn = new JButton("CANCELAR");
        cancelarBtn.addActionListener(e -> {
            this.dispose();
        });

        add(comboSet, BorderLayout.NORTH);
    }
}
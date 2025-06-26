package com.adoouade.unomas;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.frontend.*;
import com.adoouade.unomas.model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class UnomasApplication {
	// attributes
	private static IniciarSesionView iniciarSesionView;
	private static RegistrarseView registrarseView;
	private static InicioView inicioView;
	private static PerfilView perfilView;
	private static EditarPerfilView editarPerfilView;
	private static HistorialView historialView;
	private static PartidosView partidosView;
	private static BuscarPartidosView buscarPartidosView;
	private static CrearPartidoView crearPartidoView;
	private static VisorPartidosView visorPartidosView;
	private static PartidosPendientesView partidosPendientesView;
	private static PartidosSinArmarseView partidosSinArmarseView;
	private static PartidosPorJugarseView partidosPorJugarseView;
	private static PartidosEnJuegoView partidosEnJuegoView;
	private static PartidosFinalizadosView partidosFinalizadosView;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		ConfigurableApplicationContext context = SpringApplication.run(UnomasApplication.class, args);
		UsuarioController usuarioController = context.getBean(UsuarioController.class);
		PartidoController partidoController = context.getBean(PartidoController.class);

		// crear ventana principal
		JFrame frame = new JFrame("UnoMas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setLocationRelativeTo(null);
		CardLayout card = new CardLayout();
		JPanel panelCard = new JPanel(card);

		Usuario usuario = new Usuario();

		// crear pantallas e inicializar atributos
		iniciarSesionView = new IniciarSesionView(card, panelCard);
		registrarseView = new RegistrarseView(card, panelCard);
		inicioView = new InicioView(card, panelCard, usuario);
		perfilView = new PerfilView(card, panelCard, usuario);
		editarPerfilView = new EditarPerfilView(card, panelCard, usuario);
		historialView = new HistorialView(card, panelCard, usuario);
		partidosView = new PartidosView(card, panelCard, usuario);
		buscarPartidosView = new BuscarPartidosView(card, panelCard, usuario);
		crearPartidoView = new CrearPartidoView(card, panelCard, usuario);
		visorPartidosView = new VisorPartidosView(card, panelCard, usuario);
		partidosPendientesView = new PartidosPendientesView(card, panelCard, usuario, partidoController);
		partidosSinArmarseView = new PartidosSinArmarseView(card, panelCard, usuario, partidoController);
		partidosPorJugarseView = new PartidosPorJugarseView(card, panelCard, usuario, partidoController);
		partidosEnJuegoView = new PartidosEnJuegoView(card, panelCard, usuario, partidoController);
		partidosFinalizadosView = new PartidosFinalizadosView(card, panelCard, usuario, partidoController, usuarioController);

		// agregar panelCard al frame
		frame.setLayout(new BorderLayout());
		frame.add(panelCard, BorderLayout.CENTER);
		card.show(panelCard, "Iniciar Sesion");
		frame.setVisible(true);
	}
}

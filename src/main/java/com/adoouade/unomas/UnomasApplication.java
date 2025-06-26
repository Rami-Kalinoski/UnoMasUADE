package com.adoouade.unomas;

import com.adoouade.unomas.controller.DeporteController;
import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dao.DeporteDao;
import com.adoouade.unomas.frontend.*;
import com.adoouade.unomas.model.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;

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
		DeporteController deporteController = context.getBean(DeporteController.class);

		// crear ventana principal
		JFrame frame = new JFrame("UnoMas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 700);
		frame.setLocationRelativeTo(null);
		CardLayout card = new CardLayout();
		JPanel panelCard = new JPanel(card);

		Usuario usuario = usuarioController.getUsuario();

		// crear pantallas e inicializar atributos
		iniciarSesionView = new IniciarSesionView();
		registrarseView = new RegistrarseView();
		inicioView = new InicioView();

		partidosView = new PartidosView();
		buscarPartidosView = new BuscarPartidosView();
		crearPartidoView = new CrearPartidoView();
		visorPartidosView = new VisorPartidosView();
		partidosPendientesView = new PartidosPendientesView();
		partidosSinArmarseView = new PartidosSinArmarseView();
		partidosPorJugarseView = new PartidosPorJugarseView();
		partidosEnJuegoView = new PartidosEnJuegoView();
		partidosFinalizadosView = new PartidosFinalizadosView();

		iniciarSesionView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		registrarseView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		crearPartidoView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		visorPartidosView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosSinArmarseView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosPorJugarseView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosEnJuegoView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);



		// agregar panelCard al frame
		frame.setLayout(new BorderLayout());
		frame.add(panelCard, BorderLayout.CENTER);
		card.show(panelCard, "Iniciar Sesion");
		frame.setVisible(true);
	}

	public static void crearPantallasPersonalizadas(CardLayout card, JPanel panelCard, JFrame frame, UsuarioController usuarioController, PartidoController partidoController, Usuario usuario, DeporteController deporteController) {
		inicioView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		perfilView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		editarPerfilView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		historialView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		buscarPartidosView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosPendientesView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
		partidosFinalizadosView.CrearPantalla(card, panelCard, frame, usuarioController, partidoController, usuario, deporteController);
	}
}

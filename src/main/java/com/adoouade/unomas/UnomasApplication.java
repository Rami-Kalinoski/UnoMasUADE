package com.adoouade.unomas;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dao.UsuarioDao;
import com.adoouade.unomas.dao.UsuarioDeporteDao;
import com.adoouade.unomas.dto.UsuarioDeporteDto;
import com.adoouade.unomas.dto.UsuarioDto;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class UnomasApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(UnomasApplication.class, args);

		System.out.println("PRUEBA DE BASE DE DATOS\n");
		UsuarioController usuarioController = context.getBean(UsuarioController.class);
		PartidoController partidoController = context.getBean(PartidoController.class);

		JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);
		jdbcTemplate.update("DELETE FROM usuario");
		System.out.println("TODOS LOS USUARIOS BORRADOS");

		Usuario u = usuarioController.CrearUsuario(new UsuarioDto("RamiKali", "ramikalinoski@gmail.com", "12345a", "Lomas de Zamora"));
		System.out.println("Usuario creado supuestamente");
		System.out.println("Obtener usuario por ID: " + usuarioController.ObtenerUsuario(u.getId()));
		Usuario u1 = usuarioController.CrearUsuario(new UsuarioDto("JuanCarlos", "JuanCarlos@gmail.com", "12345a", "Lomas de Zamora"));
		Usuario u2 = usuarioController.CrearUsuario(new UsuarioDto("SusanaHoria", "SusanaHoria@gmail.com", "12345a", "Lomas de Zamora"));
		Usuario u3 = usuarioController.CrearUsuario(new UsuarioDto("EstebanQuito", "EstebanQuito@gmail.com", "12345a", "Lomas de Zamora"));
		System.out.println("Listar usuarios" + usuarioController.ObtenerUsuarios());
		System.out.println("Me dejas entrar con el username RamiKali, email ramikalinoski@gmail.com y password 12345a? " + usuarioController.Login(u.getUsername(), u.getEmail(), u.getContraseña()));
		u1.setUsername("Romina");
		boolean modificado = usuarioController.ModificarUsuario(new UsuarioDao().toDto(u1));
		u1 = usuarioController.ObtenerUsuario(u1.getId());
		System.out.println("A JuanCarlos ahora vamos a llamarlo Romina (" + modificado + "): " + u1 );

		System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println("CREACIÓN DE USUARIOS DEPORTE");
		UsuarioDeporte ud1 = usuarioController.CrearUsuarioDeporte(new UsuarioDeporteDto(11L, 1L, Nivel.PRINCIPIANTE, false));
		UsuarioDeporte ud2 = usuarioController.CrearUsuarioDeporte(new UsuarioDeporteDto(11L, 2L, Nivel.INTERMEDIO, false));
		UsuarioDeporte ud3 = usuarioController.CrearUsuarioDeporte(new UsuarioDeporteDto(11L, 3L, Nivel.AVANZADO, true));
		System.out.println(ud1.toString() + ud2.toString() + ud3.toString());
		System.out.println("OBTENER USUARIO DEPORTE: " + usuarioController.ObtenerUsuarioDeporte(ud1.getUsuario().getId(), ud1.getDeporte().getId()));
		System.out.println("TODOS LOS USUARIO DEPORTE: " + usuarioController.ObtenerUsuarioDeportes());
		ud2.setNivel(Nivel.AVANZADO); usuarioController.ModificarUsuarioDeporte(new UsuarioDeporteDao().toDto(ud2));
		ud2 = usuarioController.ObtenerUsuarioDeporte(ud2.getUsuario().getId(), ud2.getDeporte().getId());
		System.out.println("Cambiamos el nivel del segundo: " + ud2);

		jdbcTemplate.update("DELETE FROM usuario_deporte");
		System.out.println("TODOS LOS USUARIOS DEPORTES BORRADOS");





	}
}

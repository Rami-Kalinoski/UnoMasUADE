package com.adoouade.unomas.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Deporte {
    private Long id;
    private String nombre;
    private int cantidadJugadores;
    private List<UsuarioDeporte> usuarioDeportes;
}
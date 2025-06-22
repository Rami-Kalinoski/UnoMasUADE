package com.adoouade.unomas.model;

import com.adoouade.unomas.enums.Nivel;
import lombok.*;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class UsuarioDeporte {
    private Long id;
    private Usuario usuario;
    private Deporte deporte;
    private Nivel nivel = Nivel.PRINCIPIANTE;
    private boolean favorito = false;
    private int partidosGanados = 0;
    private int partidosPerdidos = 0;
    private int partidosEmpatados = 0;
}
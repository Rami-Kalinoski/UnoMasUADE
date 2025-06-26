package com.adoouade.unomas.model;

import lombok.*;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Reseña {
    private Long id;
    private Usuario usuario;
    private Partido partido;
    private String comentario;
    private int calificacion;
}
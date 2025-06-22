package com.adoouade.unomas.classes;

import lombok.*;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Notificacion {
    private String titulo;
    private String mensaje;
}
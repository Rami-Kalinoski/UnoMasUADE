package com.adoouade.unomas.model;

import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import lombok.*;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Participacion {
    private Long id;
    private Usuario usuario;
    private Partido partido;
    private EstadoParticipacion estado = EstadoParticipacion.PENDIENTE;
    private Resultado resultado = Resultado.INDEFINIDO;
}
package com.adoouade.unomas.model;

import com.adoouade.unomas.classes.NecesitaJugadores;
import com.adoouade.unomas.classes.PartidoArmado;
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

    public void cambiarEstado(EstadoParticipacion estado) {
        if (partido.getEstado().getClass().equals(NecesitaJugadores.class) || partido.getEstado().getClass().equals(PartidoArmado.class)) {
            this.estado = estado;
        }
        if (partido.getEstado().getClass().equals(PartidoArmado.class) && estado.equals(EstadoParticipacion.ACEPTADA) && partido.getJugadores().size()>=partido.getCantidadJugadores()) {
            partido.CambiarEstado();
        }
    }
}
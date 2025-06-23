package com.adoouade.unomas.dto;

import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import com.adoouade.unomas.model.Participacion;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacionDto {
    private Long id;
    private Long usuarioId;
    private Long partidoId;
    private EstadoParticipacion estado;
    private Resultado resultado;

    public ParticipacionDto(Long usuarioId, Long partidoId, EstadoParticipacion estado, Resultado resultado) {
        this.usuarioId = usuarioId;
        this.partidoId = partidoId;
        this.estado = estado;
        this.resultado = resultado;
    }
}
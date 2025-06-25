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
    private String estado;
    private String resultado;

    public ParticipacionDto(Long usuarioId, Long partidoId, EstadoParticipacion estado, Resultado resultado) {
        this.usuarioId = usuarioId;
        this.partidoId = partidoId;
        this.estado = estado.name();
        this.resultado = resultado.name();
    }
}
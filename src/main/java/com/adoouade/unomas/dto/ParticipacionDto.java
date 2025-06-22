package com.adoouade.unomas.dto;

import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipacionDto {
    private Long usuarioId;
    private Long partidoId;
    private EstadoParticipacion estado;
    private Resultado resultado;
}
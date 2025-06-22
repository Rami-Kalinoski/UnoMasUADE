package com.adoouade.unomas.dto;

import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDeporteDto {
    private Long id;
    private Long usuarioId;
    private Long deporteId;
    @Builder.Default
    private Nivel nivel = Nivel.PRINCIPIANTE;
    @Builder.Default
    private boolean favorito = false;
    @Builder.Default
    private int partidosGanados = 0;
    @Builder.Default
    private int partidosPerdidos = 0;
    @Builder.Default
    private int partidosEmpatados = 0;
}
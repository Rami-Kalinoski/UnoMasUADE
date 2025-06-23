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

    public UsuarioDeporteDto(Long usuarioId, Long deporteId, Nivel nivel, boolean favorito) {
        this.usuarioId = usuarioId;
        this.deporteId = deporteId;
        this.nivel = nivel;
        this.favorito = favorito;
        this.partidosGanados = 0;
        this.partidosPerdidos = 0;
        this.partidosEmpatados = 0;
    }
}
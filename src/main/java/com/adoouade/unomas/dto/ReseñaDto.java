package com.adoouade.unomas.dto;

import com.adoouade.unomas.model.Partido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReseñaDto {
    private Long id;
    private Long partidoId;
    private String comentario;
    private int calificacion;

    public ReseñaDto(Long partidoId, String comentario, int calificacion) {
        this.partidoId = partidoId;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }
}
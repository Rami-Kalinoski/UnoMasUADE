package com.adoouade.unomas.dto;

import com.adoouade.unomas.classes.Emparejador;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.interfaces.Observer;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Reseña;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDto {
    private Long id;
    private Long organizadorId;
    private Long deporteId;
    private String ubicacion;
    private LocalDate fecha;
    private LocalTime horario;
    private String estado;
    private int cantidadJugadores;
    private int duracionMinutos;

    public PartidoDto(Long organizadorId, Long deporteId, String ubicacion, LocalDate fecha, LocalTime horario, String estado, int cantidadJugadores, int duracionMinutos) {
        this.organizadorId = organizadorId;
        this.deporteId = deporteId;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.horario = horario;
        this.estado = estado;
        this.cantidadJugadores = cantidadJugadores;
        this.duracionMinutos = duracionMinutos;
    }
}
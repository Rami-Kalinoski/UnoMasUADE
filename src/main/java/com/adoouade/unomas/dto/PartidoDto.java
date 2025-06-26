package com.adoouade.unomas.dto;

import com.adoouade.unomas.classes.Emparejador;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

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
    private String emparejador;
    private int cantidadJugadores;
    private int duracionMinutos;

    public PartidoDto(Long organizadorId, Long deporteId, String ubicacion, LocalDate fecha, LocalTime horario, IEstadoPartido estado, String emparejador,  int cantidadJugadores, int duracionMinutos) {
        this.organizadorId = organizadorId;
        this.deporteId = deporteId;
        this.ubicacion = ubicacion;
        this.fecha = fecha;
        this.horario = horario;
        this.estado = estado.getClass().toString();
        this.emparejador = emparejador;
        this.cantidadJugadores = cantidadJugadores;
        this.duracionMinutos = duracionMinutos;
    }
}
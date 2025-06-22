package com.adoouade.unomas.model;

import com.adoouade.unomas.classes.Emparejador;
import com.adoouade.unomas.dao.PartidoDao;
import com.adoouade.unomas.dao.ReseñaDao;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.interfaces.Observer;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Partido {
    // attributes
    private Long id;
    private Usuario organizador;
    private Deporte deporte;
    private String ubicacion;
    private LocalDate fecha;
    private LocalTime horario;
    private IEstadoPartido estado;
    private List<Participacion> participaciones;
    private List<Reseña> reseñas;
    private Emparejador emparejador;
    private Observer observer; // notificador
    private int cantidadJugadores;
    private int duracionMinutos;

    @Autowired
    private PartidoDao partidoDao;
    @Autowired
    private ReseñaDao reseñaDao;

    // methods
    public void CambiarEstado() {
        this.estado.ManejarPartido(this);
    }
    public void CancelarPartido() {
        this.estado.CancelarPartido(this);
    }
    public boolean DeterminarConfirmaciones() { // también devuelve false si las confirmaciones superan las necesarias
        int confirmados = 0;
        for (int i=0 ; i<participaciones.size() ; i++) {
            if (participaciones.get(i).getEstado()==EstadoParticipacion.ACEPTADA) confirmados++;
        }
        return confirmados==cantidadJugadores;
    }
    public List<Usuario> getJugadores() {
        List<Usuario> jugadores = new ArrayList<>();
        for (int i=0 ; i<participaciones.size() ; i++) {
            if (participaciones.get(i).getEstado()==EstadoParticipacion.ACEPTADA) jugadores.add(participaciones.get(i).getUsuario());
        }
        return jugadores;
    }
}
package com.adoouade.unomas.classes;

import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.Participacion;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Reseña;
import com.adoouade.unomas.model.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class EnJuego implements IEstadoPartido {
    public void ManejarPartido(Partido partido) {
        LocalDateTime comienzo = LocalDateTime.of(partido.getFecha(), partido.getHorario());
        LocalDateTime fin = comienzo.plusMinutes(partido.getDuracionMinutos());
        if (LocalDateTime.now().isAfter(fin)) {
            partido.setEstado(new Finalizado());
            List<Usuario> notificados = new ArrayList<>();
            partido.getParticipaciones().forEach(participacion -> {
                notificados.add(participacion.getUsuario());
            });
            partido.getObserver().SerNotificado(notificados, new Notificacion("Partido Finalizado", "¡El partido ha terminado!"));
            FinalizarPartido(partido);
        } else {
            System.out.println("Aún no se han jugado los minutos de duración total.");
        }
    }
    public void CancelarPartido(Partido partido) {
        System.out.println("No se puede cancelar un partido una vez que está en juego.");
    }

    // private
    private void FinalizarPartido(Partido partido) {
        AsignarResultados(partido);
        TomarReseñas(partido);
    }
    private void AsignarResultados(Partido partido) { // asigna resultados al azar
        List<Participacion> participacionesAceptadas = partido.getParticipaciones().stream().filter(p -> p.getEstado() == EstadoParticipacion.ACEPTADA).toList();
        AtomicInteger pendientes = new AtomicInteger(participacionesAceptadas.size());
        boolean empatan = RandomInt(1, 5)==5 ? true : false;
        if (!empatan) {
            participacionesAceptadas.forEach(participacion -> {
                boolean gana = RandomInt(1, pendientes.getAndDecrement()) > pendientes.get() /2 ? true : false;
                if (gana) {
                    participacion.setResultado(Resultado.GANA);
                } else {
                    participacion.setResultado(Resultado.PIERDE);
                }
            });
        } else {
            participacionesAceptadas.forEach(participacion -> {
                participacion.setResultado(Resultado.EMPATA);
            });
        }
    }
    @Override
    public String toString() {
        return "EnJuego";
    }
    private void TomarReseñas(Partido partido) {
//        Scanner scanner = new Scanner(System.in);
//        String comentario = "";
//        int calificacion = 0;
//        System.out.println("Ingrese un comentario sobre el partido (o el signo ! para finalizar): "); comentario = scanner.next();
//        while (!comentario.equals("!")) {
//            System.out.println("Ingrese una calificación al partido (entre 1 y 10): "); calificacion = scanner.nextInt(); System.out.println();
//            while (calificacion<1 || calificacion>10) {
//                System.out.println("ERROR - La calificación debe ser entre 1 y 10.");
//                System.out.println("Ingrese una calificación al partido (entre 1 y 10): "); calificacion = scanner.nextInt(); System.out.println();
//            }
//            partido.getReseñas().add(new Reseña(comentario, calificacion));
//            System.out.println("Ingrese un comentario sobre el partido (o el signo ! para finalizar): "); comentario = scanner.next();
//        }
    }
    private int RandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
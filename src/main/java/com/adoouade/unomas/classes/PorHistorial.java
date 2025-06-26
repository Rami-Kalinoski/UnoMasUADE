package com.adoouade.unomas.classes;

import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Resultado;
import com.adoouade.unomas.interfaces.IEstrategiaEmparejamiento;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class PorHistorial implements IEstrategiaEmparejamiento {
    // attributes
    @Autowired
    private UsuarioController controller;
    private Integer minimosGanados = null;
    private Integer maximosGanados = null;
    private Integer minimosPerdidos = null;
    private Integer maximosPerdidos = null;

    // methods
    public void Emparejar(Partido partido) {
//        List<Usuario> usuarios = controller.ObtenerUsuarios();
//        List<Usuario> notificados = new ArrayList<>();
//        Deporte deporte = partido.getDeporte();
//        usuarios.forEach(usuario -> {
//            UsuarioDeporte usuarioDeporte = controller.ObtenerUsuarioDeporte(usuario.getUsername(), deporte.getNombre());
//            int ganados = usuarioDeporte.getPartidosGanados();
//            int perdidos = usuarioDeporte.getPartidosPerdidos();
//            boolean cumpleGanados = (minimosGanados == null || ganados >= minimosGanados) && (maximosGanados == null || ganados <= maximosGanados);
//            boolean cumplePerdidos = (minimosPerdidos == null || perdidos >= minimosPerdidos) && (maximosPerdidos == null || perdidos <= maximosPerdidos);
//            if (usuarioDeporte.isFavorito() && cumpleGanados && cumplePerdidos) {
//                notificados.add(usuario);
//                controller.CrearParticipacion(new ParticipacionDto(usuario, partido, EstadoParticipacion.PENDIENTE, Resultado.INDEFINIDO));
//            }
//        });
//        StringBuilder mensaje = new StringBuilder("¡Se ha creado un nuevo partido de " + deporte.getNombre());
//        boolean hayFiltroGanados = minimosGanados != null || maximosGanados != null;
//        boolean hayFiltroPerdidos = minimosPerdidos != null || maximosPerdidos != null;
//
//        if (hayFiltroGanados && hayFiltroPerdidos) {
//            mensaje.append(" con historial de ")
//                    .append(describirFiltro("ganados", minimosGanados, maximosGanados))
//                    .append(" y ")
//                    .append(describirFiltro("perdidos", minimosPerdidos, maximosPerdidos));
//        } else if (hayFiltroGanados) {
//            mensaje.append(" con historial de ")
//                    .append(describirFiltro("ganados", minimosGanados, maximosGanados));
//        } else if (hayFiltroPerdidos) {
//            mensaje.append(" con historial de ")
//                    .append(describirFiltro("perdidos", minimosPerdidos, maximosPerdidos));
//        } else {
//            mensaje.append(" para todo historial");
//        }
//        mensaje.append("!");
//        partido.getObserver().SerNotificado(notificados, new Notificacion("Partido relacionado a tu historial creado", mensaje.toString()));
//        if (partido.getParticipaciones().size()>=partido.getCantidadJugadores()) {
//            partido.CambiarEstado();
//        }
    }

    // privates
    private String describirFiltro(String tipo, Integer minimo, Integer maximo) {
        if (minimo != null && maximo != null) {
            return tipo + " entre " + minimo + " y " + maximo;
        } else if (minimo != null) {
            return tipo + " mínimos de " + minimo;
        } else if (maximo != null) {
            return tipo + " máximos de " + maximo;
        } else {
            return tipo + " sin restricción";
        }
    }
    @Override
    public String toString() {
        return "PorHistorial";
    }
}
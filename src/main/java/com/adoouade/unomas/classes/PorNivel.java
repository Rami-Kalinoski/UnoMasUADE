package com.adoouade.unomas.classes;

import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.dto.ParticipacionDto;
import com.adoouade.unomas.enums.EstadoParticipacion;
import com.adoouade.unomas.enums.Nivel;
import com.adoouade.unomas.enums.Resultado;
import com.adoouade.unomas.interfaces.IEstrategiaEmparejamiento;
import com.adoouade.unomas.model.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class PorNivel implements IEstrategiaEmparejamiento {
    // attributes
    @Autowired
    private UsuarioController controller;
    private Nivel nivelMinimo = null;
    private Nivel nivelMaximo = null;

    // methods
    public void Emparejar(Partido partido) {
//        List<Usuario> usuarios = controller.ObtenerUsuarios();
//        List<Usuario> notificados = new ArrayList<>();
//        Deporte deporte = partido.getDeporte();
//        usuarios.forEach(usuario -> {
//            UsuarioDeporte usuarioDeporte = controller.ObtenerUsuarioDeporte(usuario.getUsername(), deporte.getNombre());
//            Nivel nivel = usuarioDeporte.getNivel();
//            if (usuarioDeporte.isFavorito() && (nivelMinimo == null || nivel.compareTo(nivelMinimo) >= 0) && (nivelMaximo == null || nivel.compareTo(nivelMaximo) <= 0)) {
//                // notificar y crear invitación
//                notificados.add(usuario);
//                controller.CrearParticipacion(new ParticipacionDto(usuario, partido, EstadoParticipacion.PENDIENTE, Resultado.INDEFINIDO));
//            }
//        });
//        StringBuilder mensaje = new StringBuilder("¡Se ha creado un nuevo partido de " + deporte.getNombre());
//        if (nivelMinimo != null && nivelMaximo != null) {
//            mensaje.append(" con nivel mínimo de ").append(nivelMinimo)
//                    .append(" y nivel máximo de ").append(nivelMaximo);
//        } else if (nivelMinimo != null) {
//            mensaje.append(" con nivel mínimo de ").append(nivelMinimo);
//        } else if (nivelMaximo != null) {
//            mensaje.append(" con nivel máximo de ").append(nivelMaximo);
//        } else {
//            mensaje.append(" con todos los niveles");
//        }
//        mensaje.append("!");
//        partido.getObserver().SerNotificado(notificados, new Notificacion("Partido de tu nivel creado", mensaje.toString()));
//        if (partido.getParticipaciones().size()>=partido.getCantidadJugadores()) {
//            partido.CambiarEstado();
//        }
    }
}
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
public class PorCercania implements IEstrategiaEmparejamiento {
    // attributes
    @Autowired
    private UsuarioController controller;
    private String ubicacion;

    // methods
    public void Emparejar(Partido partido) {
//        List<Usuario> usuarios = controller.ObtenerUsuarios();
//        List<Usuario> notificados = new ArrayList<>();
//        Deporte deporte = partido.getDeporte();
//        usuarios.forEach(usuario -> {
//            UsuarioDeporte usuarioDeporte = controller.ObtenerUsuarioDeporte(usuario.getUsername(), deporte.getNombre());
//            if (usuarioDeporte.isFavorito() && usuario.getUbicacion().equals(partido.getUbicacion())) {
//                // notificar y crear invitación
//                notificados.add(usuario);
//                controller.CrearParticipacion(new ParticipacionDto(usuario, partido, EstadoParticipacion.PENDIENTE, Resultado.INDEFINIDO));
//            }
//        });
//        partido.getObserver().SerNotificado(notificados, new Notificacion("Partido cercano creado", "¡Se ha creado un nuevo partido de " + deporte.getNombre() + " cerca de tu zona!"));
//        if (partido.getParticipaciones().size()>=partido.getCantidadJugadores()) {
//            partido.CambiarEstado();
//        }
    }
    @Override
    public String toString() {
        return "PorCercania";
    }
}
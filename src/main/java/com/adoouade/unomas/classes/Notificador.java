package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.IEstrategiaNotificador;
import com.adoouade.unomas.interfaces.Observer;
import com.adoouade.unomas.model.Usuario;
import lombok.*;

import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Notificador implements Observer {
    // attributes
    private IEstrategiaNotificador estrategiaNotificador;

    // methods
    public void SerNotificado(List<Usuario> usuarios, Notificacion notificacion) {
        usuarios.forEach(usuario -> {
            Notificar(usuario, notificacion);
        });
    }
    public void Notificar(Usuario usuario, Notificacion notificacion) {
        estrategiaNotificador.Notificar(usuario, notificacion);
    }
    public void CambiarEstrategia(IEstrategiaNotificador estrategiaNotificador) {
        this.estrategiaNotificador = estrategiaNotificador;
    }
}
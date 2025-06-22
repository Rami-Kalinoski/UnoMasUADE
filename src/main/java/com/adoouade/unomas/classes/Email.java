package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.EmailAdapter;
import com.adoouade.unomas.interfaces.IEstrategiaNotificador;
import com.adoouade.unomas.model.Usuario;

public class Email implements IEstrategiaNotificador {
    // attributes
    private EmailAdapter adapter;

    // methods
    public void Notificar(Usuario usuario, Notificacion notificacion) {
        adapter.Notificar(usuario, notificacion);
    }
}
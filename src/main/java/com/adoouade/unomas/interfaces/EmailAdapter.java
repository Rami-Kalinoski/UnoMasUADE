package com.adoouade.unomas.interfaces;

import com.adoouade.unomas.classes.Notificacion;
import com.adoouade.unomas.model.Usuario;

public interface EmailAdapter {
    void Notificar(Usuario usuario, Notificacion notificacion);
}
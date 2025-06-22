package com.adoouade.unomas.interfaces;

import com.adoouade.unomas.classes.Notificacion;
import com.adoouade.unomas.model.Usuario;

public interface IEstrategiaNotificador {
    void Notificar(Usuario usuario, Notificacion notificacion);
}
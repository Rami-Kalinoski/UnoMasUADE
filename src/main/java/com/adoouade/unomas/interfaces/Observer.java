package com.adoouade.unomas.interfaces;

import com.adoouade.unomas.classes.Notificacion;
import com.adoouade.unomas.model.Usuario;

import java.util.List;

public interface Observer {
    void SerNotificado(List<Usuario> usuarios, Notificacion notificacion);
}
package com.adoouade.unomas.interfaces;

import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.adoouade.unomas.model.UsuarioDeporte;

public interface IEstrategiaEmparejamiento {
    void Emparejar(Partido partido);
    boolean Cumple(UsuarioDeporte usuarioDeporte);
}
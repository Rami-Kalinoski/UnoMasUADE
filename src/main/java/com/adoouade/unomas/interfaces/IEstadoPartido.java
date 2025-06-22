package com.adoouade.unomas.interfaces;

import com.adoouade.unomas.model.Partido;

public interface IEstadoPartido {
    void ManejarPartido(Partido partido);
    void CancelarPartido(Partido partido);
}
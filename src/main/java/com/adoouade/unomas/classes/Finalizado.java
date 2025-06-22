package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.Partido;

public class Finalizado implements IEstadoPartido {
    public void ManejarPartido(Partido partido) {
        System.out.println("El partido ya ha finalizado.");
    }
    public void CancelarPartido(Partido partido) {
        System.out.println("El partido ya ha finalizado.");
    }
}
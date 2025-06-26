package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.IEstadoPartido;
import com.adoouade.unomas.model.Partido;

public class Cancelado implements IEstadoPartido {
    public void ManejarPartido(Partido partido) {
        System.out.println("El partido ya se ha cancelado.");
    }
    public void CancelarPartido(Partido partido) {
        System.out.println("El partido ya se ha cancelado.");
    }
    @Override
    public String toString() {
        return "Cancelado";
    }
}
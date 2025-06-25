package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.IEstrategiaEmparejamiento;
import com.adoouade.unomas.model.Partido;
import lombok.Getter;

@Getter
public class Emparejador {
    // attributes
    private IEstrategiaEmparejamiento estrategia;

    // methods
    public void CambiarEstrategia(IEstrategiaEmparejamiento estrategia) {
        this.estrategia = estrategia;
    }
    public void Emparejar(Partido partido) {
        this.estrategia.Emparejar(partido);
    }
}
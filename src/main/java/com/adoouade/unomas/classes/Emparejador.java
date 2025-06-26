package com.adoouade.unomas.classes;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.dao.PartidoDao;
import com.adoouade.unomas.dto.PartidoDto;
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
    public void CambiarEstrategia(Partido partido, IEstrategiaEmparejamiento estrategia) {
        this.estrategia = estrategia;
        PartidoController controller = new PartidoController();
        PartidoDto dto = new PartidoDto(partido.getId(), partido.getOrganizador().getId(), partido.getDeporte().getId(), partido.getUbicacion(), partido.getFecha(), partido.getHorario(), partido.getEstado().getClass().toString(), estrategia.getClass().toString(), partido.getCantidadJugadores(), partido.getDuracionMinutos());
        controller.ModificarPartido(dto);
    }
    public void Emparejar(Partido partido) {
        this.estrategia.Emparejar(partido);
    }
}
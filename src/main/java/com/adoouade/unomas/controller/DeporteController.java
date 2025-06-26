package com.adoouade.unomas.controller;

import com.adoouade.unomas.dao.DeporteDao;
import com.adoouade.unomas.dao.UsuarioDao;
import com.adoouade.unomas.model.Deporte;
import com.adoouade.unomas.model.UsuarioDeporte;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Controller
public class DeporteController {
    // attributes
    @Autowired
    private DeporteDao deporteDao;

    // methods
    public Deporte obtenerDeporte(Long id) {
        return deporteDao.obtenerDeporte(id);
    }

    public List<Deporte> obtenerDeportes() {
        return deporteDao.obtenerDeportes();
    }
}
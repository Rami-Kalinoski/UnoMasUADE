package com.adoouade.unomas.frontend;

import com.adoouade.unomas.controller.PartidoController;
import com.adoouade.unomas.controller.UsuarioController;
import org.springframework.beans.factory.annotation.Autowired;

public class Events {
    // attributes
    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private PartidoController partidoController;

    // methods
    public static void Login(String username, String email, String password) {

    }
}
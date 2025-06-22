package com.adoouade.unomas.model;

import com.adoouade.unomas.dao.ParticipacionDao;
import com.adoouade.unomas.dao.UsuarioDao;
import com.adoouade.unomas.dao.UsuarioDeporteDao;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data @ToString @EqualsAndHashCode
@AllArgsConstructor @NoArgsConstructor
public class Usuario {
    private Long id;
    private String username;
    private String email;
    private String contraseña;
    private String ubicacion;
    private List<UsuarioDeporte> usuarioDeportes;

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private UsuarioDeporteDao usuarioDeporteDao;
    @Autowired
    private ParticipacionDao participacionDao;
}
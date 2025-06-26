package com.adoouade.unomas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String username;
    private String email;
    private String contraseña;
    private String ubicacion;
    private String notificaciones;

    public UsuarioDto(String username, String email, String contraseña, String ubicacion, String notificaciones) {
        this.username = username;
        this.email = email;
        this.contraseña = contraseña;
        this.ubicacion = ubicacion;
        this.notificaciones = notificaciones;
    }


}
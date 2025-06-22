package com.adoouade.unomas.classes;

import com.adoouade.unomas.interfaces.EmailAdapter;
import com.adoouade.unomas.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class JavaMailAdapter implements EmailAdapter {
    // attributes
    @Autowired
    private JavaMailSender mailSender;

    // methods
    public void Notificar(Usuario usuario, Notificacion notificacion) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(usuario.getEmail());
        mensaje.setSubject(notificacion.getMensaje());
        mensaje.setText(notificacion.getTitulo());

        mailSender.send(mensaje);
        System.out.println("Correo enviado exitosamente a la dirección de email " + usuario.getEmail());
    }
}
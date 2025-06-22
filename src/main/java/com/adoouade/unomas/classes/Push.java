package com.adoouade.unomas.classes;

import com.adoouade.unomas.controller.UsuarioController;
import com.adoouade.unomas.interfaces.IEstrategiaNotificador;
import com.adoouade.unomas.model.Partido;
import com.adoouade.unomas.model.Usuario;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;

public class Push implements IEstrategiaNotificador {
    // attributes
    @Autowired
    private UsuarioController controller;

    // methods
    public void Notificar(Usuario usuario, Notificacion notificacion) {
        try {
            Message message = Message.builder()
                    .setToken(controller.getTokenLogueado())
                    .setNotification(Notification.builder()
                            .setTitle(notificacion.getTitulo())
                            .setBody(notificacion.getMensaje())
                            .build())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Notificación enviada: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
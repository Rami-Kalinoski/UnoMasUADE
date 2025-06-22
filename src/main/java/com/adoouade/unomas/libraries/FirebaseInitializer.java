package com.adoouade.unomas.libraries;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Component
public class FirebaseInitializer {
    @PostConstruct
    public static void init() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/firebase-adminsdk.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
            System.out.println("Firebase inicializado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
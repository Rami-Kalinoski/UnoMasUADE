package com.adoouade.unomas.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@Component
public class DatabaseInitializer {

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @PostConstruct
    public void init() {
        try {
            String url = "jdbc:mysql://localhost:3306/?useSSL=false&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS unomas");
            stmt.close();
            connection.close();
            System.out.println("Base de datos 'unomas' verificada o creada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al crear/verificar la base de datos: " + e.getMessage());
        }
    }
}
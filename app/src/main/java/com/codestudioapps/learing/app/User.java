package com.codestudioapps.learing.app;

public class User {
    private String name;
    private String user;
    private String email;
    private String id;
    private String password;

    // Constructor vacío requerido para Firebase Realtime Database
    public User() {
    }

    public User(String name, String user, String email, String id, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


// Getters y setters (opcional) para acceder a los datos
    // Puedes generarlos automáticamente en Android Studio mediante clic derecho > Generate > Getters and Setters
}


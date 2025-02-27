package com.tecnocampus.cattecnocampusapps2324p4lautaromartinezcenatiempo.model;

public class User {
    private String id;
    private String email;
    private String password;

    // Constructor, getters y setters

    public User() {}

    public User(String id) {
        this.id = id;
    }

    public User(String id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
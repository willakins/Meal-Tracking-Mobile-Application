package com.model;

public class User {
    private String username;
    private String password;
    private int height;
    private int weight;
    private boolean isMale;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

}

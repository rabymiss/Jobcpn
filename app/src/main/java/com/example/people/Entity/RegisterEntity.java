package com.example.people.Entity;

public class RegisterEntity {
    @Override
    public String toString() {
        return "RegisterEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public RegisterEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    private  String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

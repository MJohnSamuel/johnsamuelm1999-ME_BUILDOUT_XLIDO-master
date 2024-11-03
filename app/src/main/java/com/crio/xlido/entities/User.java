package com.crio.xlido.entities;

public class User {
    private final Long id;
    private String password;
    private String email;

    public User(String email,String password) {
        this.id = null;
        this.email = email;
        this.password = password;
    }
    public User(long id, User entity) {
        this.id = id;
        this.email = entity.email;
        this.password = entity.password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User ID: " + id;
    }

}
